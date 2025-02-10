package com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dicoding.nrahmatd.picodiploma.core.baseview.BaseFragment
import com.dicoding.nrahmatd.picodiploma.core.model.TVModel
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.adapter.TVAdapter
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.constant.Constant.Companion.TVTYPE
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.databinding.FragmentTvBinding
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.activity.DetailActivity
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.viewmodel.ListDataViewModel

/**
 *
 * @author Nur Rahmat Dwi Riyanto.
 */

class TVFragment : BaseFragment<FragmentTvBinding>(), SwipeRefreshLayout.OnRefreshListener {

    private var tvList = ArrayList<TVModel>()
    private val listDataViewModel: ListDataViewModel by activityViewModels()
    private lateinit var tvAdapter: TVAdapter

    private fun setupView() {
        binding.rvTv.setHasFixedSize(true)
        binding.swipeTv.setOnRefreshListener(this)

        tvAdapter = TVAdapter(tvList)
        binding.rvTv.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = tvAdapter
        }
        tvAdapter.onTvItemClickListener = { tv -> showSelectedMovie(tv) }
    }

    private fun showData(refreshStatus: Boolean) {
        binding.swipeTv.isRefreshing = true
        tvAdapter.populateTVData(refreshStatus, listDataViewModel.getTvShowList())
        binding.swipeTv.isRefreshing = false
    }

    private fun showSelectedMovie(tv: TVModel) {
        val iDetail = Intent(requireActivity(), DetailActivity::class.java)
        iDetail.putExtra("loadFrom", TVTYPE)
        iDetail.putExtra("tv_id", tv.id)
        startActivity(iDetail)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("dataList", tvList)
    }

    override fun onRefresh() = showData(true)

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTvBinding
        get() = FragmentTvBinding::inflate

    override fun setup() = with(binding) {
        setupView()
        showData(false)
    }
}
