package com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dicoding.nrahmatd.picodiploma.core.baseview.BaseFragment
import com.dicoding.nrahmatd.picodiploma.core.model.MovieModel
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.adapter.MovieAdapter
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.constant.Constant.Companion.MOVIETYPE
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.databinding.FragmentMovieBinding
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.activity.DetailActivity
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.viewmodel.ListDataViewModel

/**
 *
 * @author Nur Rahmat Dwi Riyanto.
 */

class MovieFragment : BaseFragment<FragmentMovieBinding>(), SwipeRefreshLayout.OnRefreshListener {

    private var movieList = ArrayList<MovieModel>()
    private val listDataViewModel: ListDataViewModel by activityViewModels()
    private lateinit var movieAdapter: MovieAdapter

    private fun setupView() {
        binding.rvMovie.setHasFixedSize(true)
        binding.swipeMovie.setOnRefreshListener(this)

        movieAdapter = MovieAdapter(movieList)
        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = movieAdapter
        }
        movieAdapter.onMovieItemClickListener = { movie -> showSelectedMovie(movie) }
    }

    private fun showData(refreshStatus: Boolean) {
        binding.swipeMovie.isRefreshing = true
        movieAdapter.populateMovieData(refreshStatus, listDataViewModel.getMovieList())
        binding.swipeMovie.isRefreshing = false
    }

    private fun showSelectedMovie(movie: MovieModel) {
        val iDetail = Intent(requireActivity(), DetailActivity::class.java)
        iDetail.putExtra("loadFrom", MOVIETYPE)
        iDetail.putExtra("movie_id", movie.id)
        startActivity(iDetail)
    }

    override fun onRefresh() = showData(true)

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieBinding
        get() = FragmentMovieBinding::inflate

    override fun setup() = with(binding) {
        setupView()
        showData(false)
    }
}
