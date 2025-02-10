package com.dicoding.nrahmatd.picodiploma.moviecatalogue.app

import android.app.Application
import com.dicoding.nrahmatd.picodiploma.core.utils.CustomCrashHandler
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.BuildConfig
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.R
import com.nrahmatdev.androidcontext.AndroidContext

class MovieApp : AndroidContext() {

    private var appName: String = ""

    override fun onCreate() {
        super.onCreate()
        instance = this
        appName = getString(R.string.app_name)
        setupChuckerErrors()
    }

    companion object {
        @get:Synchronized
        var instance: Application? = null
            private set
    }

    private fun setupChuckerErrors() {
        if (BuildConfig.DEBUG) {
            val systemHandler = Thread.getDefaultUncaughtExceptionHandler()
            Thread.setDefaultUncaughtExceptionHandler(CustomCrashHandler(systemHandler, this))
        }
    }
}
