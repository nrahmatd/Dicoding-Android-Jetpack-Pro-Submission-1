package com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.fragment

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dicoding.nrahmatd.R
import com.dicoding.nrahmatd.databinding.FragmentMainFavoriteBinding
import com.dicoding.nrahmatd.picodiploma.core.baseview.BaseFragment
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.adapter.SectionPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 *
 * @author Nur Rahmat Dwi Riyanto
 */

class MainFavoriteFragment : BaseFragment<FragmentMainFavoriteBinding>(), TabLayout.OnTabSelectedListener {

    private fun setFragment() {
        val fragments = ArrayList<Fragment>()
        fragments.add(MovieFavoriteFragment())
        fragments.add(TVFavoriteFragment())

        val titles = arrayOf(
            getString(R.string.movie_tab_title),
            getString(R.string.tv_tab_title)
        )

        binding.viewPager.adapter = SectionPagerAdapter(requireActivity(), fragments)
        binding.viewPager.offscreenPageLimit = fragments.size
        binding.tabLayout.addOnTabSelectedListener(this@MainFavoriteFragment)

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->
            tab.text = titles[position]
        }.attach()

        setOnSelectedView()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainFavoriteBinding
        get() = FragmentMainFavoriteBinding::inflate

    override fun setup() {
        setFragment()
    }

    override fun onTabSelected(tab: TabLayout.Tab) =
        setOnSelectedTab(true, tab)

    override fun onTabUnselected(tab: TabLayout.Tab) = setOnSelectedTab(false, tab)

    override fun onTabReselected(tab: TabLayout.Tab) {}

    private fun setOnSelectedTab(selected: Boolean, tab: TabLayout.Tab) {
        if (selected) {
            val tabs =
                (binding.tabLayout.getChildAt(0) as ViewGroup).getChildAt(tab.position) as LinearLayout
            val tabTextView = tabs.getChildAt(1) as TextView
            tabTextView.setTypeface(tabTextView.typeface, Typeface.BOLD)
        } else {
            val tabs =
                (binding.tabLayout.getChildAt(0) as ViewGroup).getChildAt(tab.position) as LinearLayout
            val tabTextView = tabs.getChildAt(1) as TextView
            tabTextView.setTypeface(null, Typeface.NORMAL)
        }
    }

    private fun setOnSelectedView() {
        val tab = binding.tabLayout.getTabAt(0)
        if (tab != null) {
            val tabs =
                (binding.tabLayout.getChildAt(0) as ViewGroup).getChildAt(tab.position) as LinearLayout
            val tabTextView = tabs.getChildAt(1) as TextView
            tabTextView.setTypeface(tabTextView.typeface, Typeface.BOLD)
            tab.select()
        }
    }
}
