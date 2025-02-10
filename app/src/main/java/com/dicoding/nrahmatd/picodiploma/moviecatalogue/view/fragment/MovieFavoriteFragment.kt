package com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dicoding.nrahmatd.picodiploma.core.baseview.BaseFragment
import com.dicoding.nrahmatd.picodiploma.core.model.MovieModel
import com.dicoding.nrahmatd.picodiploma.core.utils.GlobalVariable
import com.dicoding.nrahmatd.picodiploma.core.utils.getNotify
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.adapter.MovieAdapter
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.constant.Constant.Companion.MOVIETYPE
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.database.MovieFavoriteHelper
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.databinding.FragmentMovieFavoriteBinding
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.activity.DetailActivity
import io.reactivex.disposables.CompositeDisposable

/**
 *
 * @author Nur Rahmat Dwi Riyanto.
 */

class MovieFavoriteFragment : BaseFragment<FragmentMovieFavoriteBinding>(),
    SwipeRefreshLayout.OnRefreshListener {

    private var movieList = ArrayList<MovieModel>()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieFavoriteHelper: MovieFavoriteHelper

    private val compositeDisposable = CompositeDisposable()

    private fun initLocalStorage() {
        movieFavoriteHelper = MovieFavoriteHelper.getInstance(requireActivity())
        movieFavoriteHelper.open()
    }

    private fun setupView() {
        binding.rvMovieFavorite.setHasFixedSize(true)
        binding.swipeMovieFavorite.setOnRefreshListener(this)

        movieAdapter = MovieAdapter(movieList)
        binding.rvMovieFavorite.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = movieAdapter
        }
        movieAdapter.onMovieItemClickListener = { movie -> showSelectedMovie(movie) }
    }

    private fun showDataFromLocal() {
        binding.swipeMovieFavorite.isRefreshing = true
        movieList.clear()
        movieList.addAll(movieFavoriteHelper.getAllMoviesFavorite())
        movieAdapter.notifyDataSetChanged()
        if (movieList.size > 0)
            binding.movieDataEmpty.visibility = View.GONE
        else
            binding.movieDataEmpty.visibility = View.VISIBLE

        binding.swipeMovieFavorite.isRefreshing = false
    }

    private fun showSelectedMovie(movie: MovieModel) {
        val iDetail = Intent(requireActivity(), DetailActivity::class.java)
        iDetail.putExtra("loadFrom", MOVIETYPE)
        iDetail.putExtra("movie_id", movie.id)
        startActivity(iDetail)
    }

    override fun onRefresh() = showDataFromLocal()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieFavoriteBinding
        get() = FragmentMovieFavoriteBinding::inflate

    override fun setup() = with(binding) {
        initLocalStorage()
        setupView()
        showDataFromLocal()
        initNotify()
    }

    private fun initNotify() = getNotify(compositeDisposable) {
        when (it.TAG) {
            GlobalVariable.NOTIFY_MOVIE -> showDataFromLocal()
        }
    }

    override fun onDestroy() {
        movieFavoriteHelper.close()
        super.onDestroy()
    }
}
