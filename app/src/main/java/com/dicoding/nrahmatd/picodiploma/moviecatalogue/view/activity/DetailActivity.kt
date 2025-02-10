package com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.nrahmatd.picodiploma.core.baseview.BaseActivity
import com.dicoding.nrahmatd.picodiploma.core.model.MovieModel
import com.dicoding.nrahmatd.picodiploma.core.model.TVModel
import com.dicoding.nrahmatd.picodiploma.core.utils.GlobalVariable
import com.dicoding.nrahmatd.picodiploma.core.utils.sendNotify
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.R
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.constant.Constant.Companion.MOVIETYPE
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.constant.Constant.Companion.TVTYPE
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.database.MovieFavoriteHelper
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.database.TVFavoriteHelper
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.databinding.ActivityDetailBinding
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.utils.EspressoIdlingResource
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.viewmodel.DetailDataViewModel

/**
 *
 * @author Nur Rahmat Dwi Riyanto
 */

class DetailActivity : BaseActivity<ActivityDetailBinding>(), View.OnClickListener {

    private var movieId = 0
    private var tvId = 0
    private lateinit var loadFrom: Any
    private lateinit var detailMovieModel: MovieModel
    private lateinit var detailTvModel: TVModel
    private lateinit var tvFavoriteHelper: TVFavoriteHelper
    private lateinit var movieFavoriteHelper: MovieFavoriteHelper
    private lateinit var menu: Menu
    private val detailDataViewModel: DetailDataViewModel by viewModels()

    private fun initComponent() {
        tvFavoriteHelper = TVFavoriteHelper.getInstance(applicationContext)
        movieFavoriteHelper = MovieFavoriteHelper.getInstance(applicationContext)
        tvFavoriteHelper.open()
        movieFavoriteHelper.open()
    }

    private fun intentValidate() {
        if (intent.extras != null) {
            loadFrom = intent.extras?.getInt("loadFrom") ?: 0
            when (loadFrom) {
                MOVIETYPE -> movieId = intent.getIntExtra("movie_id", 0)
                TVTYPE -> tvId = intent.getIntExtra("tv_id", 0)
            }
        }
    }

    private fun setupView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (loadFrom == MOVIETYPE) {
            supportActionBar?.title = detailMovieModel.titleMovie
            Glide.with(this)
                .load(detailMovieModel.imgMovie)
                .apply(RequestOptions().override(350, 550))
                .into(binding.imgdetail)
            binding.txtdetailTitle.text = detailMovieModel.titleMovie
            binding.txtdetailReleased.text = detailMovieModel.dateMovie
            binding.txtdetailLanguage.text = detailMovieModel.langMovie
            binding.txtdetailRuntime.text = detailMovieModel.runtimeMovie
            binding.txtdetailOverview.text = detailMovieModel.descMovie
            binding.txtdetailGenres.text = detailMovieModel.genresMovie
        } else if (loadFrom == TVTYPE) {
            supportActionBar?.title = detailTvModel.titleTV
            Glide.with(this)
                .load(detailTvModel.imgTV)
                .apply(RequestOptions().override(350, 550))
                .into(binding.imgdetail)
            binding.txtdetailTitle.text = detailTvModel.titleTV
            binding.txtdetailReleased.text = detailTvModel.dateTV
            binding.txtdetailLanguage.text = detailTvModel.langTV
            binding.txtdetailRuntime.text = detailTvModel.runtimeTV
            binding.txtdetailOverview.text = detailTvModel.descTV
            binding.txtdetailGenres.text = detailTvModel.genresTV
        }
        binding.imgdetailFb.setOnClickListener(this)
        binding.imgdetailTwt.setOnClickListener(this)
        binding.imgdetailInsta.setOnClickListener(this)

        EspressoIdlingResource.decrement()
    }

    private fun showData() {
        showLoading(true)
        when (loadFrom) {
            MOVIETYPE -> {
                detailMovieModel = detailDataViewModel.getMovieDetail(movieId)
                binding.detailGroup.visibility = View.VISIBLE
                setupView()
                showLoading(false)
            }
            TVTYPE -> {
                detailTvModel = detailDataViewModel.getTvDetail(tvId)
                binding.detailGroup.visibility = View.VISIBLE
                setupView()
                showLoading(false)
            }
        }
    }

    override fun onClick(v: View) {
        Toast.makeText(this, resources.getString(R.string.development_status), Toast.LENGTH_LONG)
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        this.menu = menu
        checkExistingFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite)
            addFavorite()
        return super.onOptionsItemSelected(item)
    }

    private fun showLoading(load: Boolean) {
        if (load)
            binding.progressDetail.visibility = View.VISIBLE
        else
            binding.progressDetail.visibility = View.GONE
    }

    private fun checkExistingFavorite() {
        when (loadFrom) {
            MOVIETYPE -> {
                val dataIsAlready: Boolean = movieFavoriteHelper.selectMovieData(movieId)

                if (dataIsAlready)
                    menu.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
            }

            TVTYPE -> {
                val dataIsAlready: Boolean = tvFavoriteHelper.selectTVData(tvId)

                if (dataIsAlready)
                    menu.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
            }
        }
    }

    private fun addFavorite() {
        when (loadFrom) {

            MOVIETYPE -> {
                val dataIsAlready: Boolean = movieFavoriteHelper.selectMovieData(movieId)

                if (dataIsAlready) {
                    val deleteResult = movieId.let { movieFavoriteHelper.deleteMovieData(it) }
                    if (deleteResult > 0)
                        Toast.makeText(
                            this,
                            getString(R.string.favorite_delete_success),
                            Toast.LENGTH_LONG
                        ).show()
                    menu.getItem(0).icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
                } else {
                    val insertResult: Long = movieFavoriteHelper.insertMovieData(detailMovieModel)

                    if (insertResult != 1L) {
                        if (insertResult > 0) {
                            Toast.makeText(
                                this,
                                "$${resources.getString(R.string.favorite_save_success)}",
                                Toast.LENGTH_LONG
                            ).show()
                            menu.getItem(0).icon =
                                ContextCompat.getDrawable(this, R.drawable.ic_favorite)
                        }
                    } else {
                        Toast.makeText(
                            this,
                            resources.getString(R.string.favorite_save_error),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                sendNotify(GlobalVariable.NOTIFY_MOVIE, null)
            }

            TVTYPE -> {

                val dataIsAlready: Boolean = tvFavoriteHelper.selectTVData(tvId)

                if (dataIsAlready) {
                    val deleteResult = tvId.let { tvFavoriteHelper.deleteTVData(it) }
                    if (deleteResult > 0)
                        Toast.makeText(
                            this,
                            getString(R.string.favorite_delete_success),
                            Toast.LENGTH_LONG
                        ).show()
                    menu.getItem(0).icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
                } else {
                    val insertResult: Long = tvFavoriteHelper.insertTVData(detailTvModel)

                    if (insertResult != 1L) {
                        if (insertResult > 0) {
                            Toast.makeText(
                                this,
                                getString(R.string.favorite_save_success),
                                Toast.LENGTH_LONG
                            ).show()
                            menu.getItem(0).icon =
                                ContextCompat.getDrawable(this, R.drawable.ic_favorite)
                        }
                    } else {
                        Toast.makeText(
                            this,
                            resources.getString(R.string.favorite_save_error),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                sendNotify(GlobalVariable.NOTIFY_TV, null)
            }
        }
    }

    override val bindingInflater: (LayoutInflater) -> ActivityDetailBinding
        get() = ActivityDetailBinding::inflate

    override fun setup(savedInstanceState: Bundle?) {
        EspressoIdlingResource.increment()
        initComponent()
        intentValidate()
        showData()
    }
}
