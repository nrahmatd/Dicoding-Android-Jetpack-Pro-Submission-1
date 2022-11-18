package com.dicoding.nrahmatd.picodiploma.moviecatalogue.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.ArrayList

/**
 *
 * @author Nur Rahmat Dwi Riyanto
 */

class SectionPagerAdapter(
    fragmentActivity: FragmentActivity,
    private var fragments: ArrayList<Fragment>
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
