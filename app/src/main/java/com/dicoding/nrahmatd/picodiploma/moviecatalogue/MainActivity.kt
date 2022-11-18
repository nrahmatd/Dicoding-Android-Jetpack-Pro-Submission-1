package com.dicoding.nrahmatd.picodiploma.moviecatalogue

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.dicoding.nrahmatd.R
import com.dicoding.nrahmatd.databinding.ActivityMainBinding
import com.dicoding.nrahmatd.picodiploma.core.baseview.BaseActivity
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.adapter.SectionPagerAdapter
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.activity.AboutActivity
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.fragment.MainFavoriteFragment
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.fragment.MainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 *
 * @author Nur Rahmat Dwi Riyanto
 */

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var mainFragment: MainFragment

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_settings ->
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))

            R.id.action_about ->
                startActivity(Intent(this@MainActivity, AboutActivity::class.java))
        }
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
        supportActionBar?.elevation = 0f
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.navigation_movie -> {
                    setActionBarTitle(getString(R.string.title_menu_home))
                    binding.viewPager.setCurrentItem(0, true)
                    mainFragment.setOnSelectedView(0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_tv -> {
                    setActionBarTitle(getString(R.string.title_menu_home))
                    binding.viewPager.setCurrentItem(0, true)
                    mainFragment.setOnSelectedView(1)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_favorite -> {
                    setActionBarTitle(getString(R.string.title_menu_favorite))
                    binding.viewPager.setCurrentItem(1, true)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setup(savedInstanceState: Bundle?) = with(binding) {
        setActionBarTitle(resources.getString(R.string.title_menu_home))
        binding.navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setFragment()
    }

    private fun setFragment() {
        mainFragment = MainFragment()

        val fragments = ArrayList<Fragment>()
        fragments.add(mainFragment)
        fragments.add(MainFavoriteFragment())
        binding.viewPager.adapter = SectionPagerAdapter(this, fragments)
        binding.viewPager.offscreenPageLimit = fragments.size
        binding.viewPager.isUserInputEnabled = false
    }

    fun setSelectedNav(position: Int) {
        when (position) {
            0 -> binding.navView.selectedItemId = R.id.navigation_movie
            1 -> binding.navView.selectedItemId = R.id.navigation_tv
        }
    }
}
