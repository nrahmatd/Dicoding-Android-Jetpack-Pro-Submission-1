package com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.fragment

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.dicoding.nrahmatd.R
import com.dicoding.nrahmatd.databinding.FragmentMainBinding
import com.dicoding.nrahmatd.picodiploma.core.baseview.BaseFragment
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.MainActivity
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.adapter.SectionPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 *
 * @author Nur Rahmat Dwi Riyanto
 */

class MainFragment : BaseFragment<FragmentMainBinding>(), TabLayout.OnTabSelectedListener {

    private lateinit var viewPager2PageChangeCallback: ViewPager2.OnPageChangeCallback

    private fun setFragment() {
        val fragments = ArrayList<Fragment>()
        fragments.add(MovieFragment())
        fragments.add(TVFragment())

        val titles = arrayOf(
            getString(R.string.movie_tab_title),
            getString(R.string.tv_tab_title)
        )

        binding.viewPager.adapter = SectionPagerAdapter(requireActivity(), fragments)
        binding.viewPager.offscreenPageLimit = fragments.size
        binding.tabLayout.addOnTabSelectedListener(this@MainFragment)

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->
            tab.text = titles[position]
        }.attach()

        viewPager2PageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                (requireActivity() as MainActivity).setSelectedNav(position)
            }
        }
        binding.viewPager.registerOnPageChangeCallback(viewPager2PageChangeCallback)

        setOnSelectedView(0)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

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

    fun setOnSelectedView(position: Int) {
        val tab = binding.tabLayout.getTabAt(position)
        if (tab != null) {
            val tabs =
                (binding.tabLayout.getChildAt(0) as ViewGroup).getChildAt(tab.position) as LinearLayout
            val tabTextView = tabs.getChildAt(1) as TextView
            tabTextView.setTypeface(tabTextView.typeface, Typeface.BOLD)
            tab.select()
        }
    }
}
