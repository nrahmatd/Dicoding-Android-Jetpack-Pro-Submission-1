package com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import com.dicoding.nrahmatd.picodiploma.core.baseview.BaseActivity
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.R
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.databinding.ActivityAboutBinding

class AboutActivity : BaseActivity<ActivityAboutBinding>() {

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override val bindingInflater: (LayoutInflater) -> ActivityAboutBinding
        get() = ActivityAboutBinding::inflate

    override fun setup(savedInstanceState: Bundle?) {
        if (supportActionBar != null) {
            supportActionBar?.title = resources.getString(R.string.about)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }
}
