package com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dicoding.nrahmatd.databinding.FragmentTvFavoriteBinding
import com.dicoding.nrahmatd.picodiploma.core.baseview.BaseFragment
import com.dicoding.nrahmatd.picodiploma.core.utils.GlobalVariable
import com.dicoding.nrahmatd.picodiploma.core.utils.getNotify
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.adapter.TVAdapter
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.constant.Constant.Companion.TVTYPE
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.database.TVFavoriteHelper
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.model.TVModel
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.activity.DetailActivity
import io.reactivex.disposables.CompositeDisposable

/**
 *
 * @author Nur Rahmat Dwi Riyanto.
 */

class TVFavoriteFragment : BaseFragment<FragmentTvFavoriteBinding>(),
    SwipeRefreshLayout.OnRefreshListener {

    private var tvList = ArrayList<TVModel>()
    private lateinit var tvAdapter: TVAdapter
    private lateinit var tvFavoriteHelper: TVFavoriteHelper

    private val compositeDisposable = CompositeDisposable()

    private fun initLocalStorage() {
        tvFavoriteHelper = TVFavoriteHelper.getInstance(requireActivity())
        tvFavoriteHelper.open()
    }

    private fun setupView() {
        binding.rvTvFavorite.setHasFixedSize(true)
        binding.swipeTvFavorite.setOnRefreshListener(this)

        tvAdapter = TVAdapter(tvList)
        binding.rvTvFavorite.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = tvAdapter
        }
        tvAdapter.onTvItemClickListener = { tv -> showSelectedMovie(tv) }
    }

    private fun showDataFromLocal() {
        binding.swipeTvFavorite.isRefreshing = true
        tvList.clear()
        tvList.addAll(tvFavoriteHelper.getAllTVFavorite())
        tvAdapter.notifyDataSetChanged()
        if (tvList.size > 0)
            binding.tvFavoriteDataEmpty.visibility = View.GONE
        else
            binding.tvFavoriteDataEmpty.visibility = View.VISIBLE

        binding.swipeTvFavorite.isRefreshing = false
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

    override fun onRefresh() = showDataFromLocal()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTvFavoriteBinding
        get() = FragmentTvFavoriteBinding::inflate

    override fun setup() = with(binding) {
        initLocalStorage()
        setupView()
        showDataFromLocal()
        initNotify()
    }

    private fun initNotify() = getNotify(compositeDisposable) {
        when (it.TAG) {
            GlobalVariable.NOTIFY_TV -> showDataFromLocal()
        }
    }

    override fun onDestroy() {
        tvFavoriteHelper.close()
        super.onDestroy()
    }
}
