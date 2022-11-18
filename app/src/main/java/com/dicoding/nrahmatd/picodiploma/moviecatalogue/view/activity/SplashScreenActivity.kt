package com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import com.daimajia.numberprogressbar.OnProgressBarListener
import com.dicoding.nrahmatd.databinding.ActivitySplashScreenBinding
import com.dicoding.nrahmatd.picodiploma.core.baseview.BaseActivity
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.MainActivity
import java.util.Timer
import java.util.TimerTask
import kotlinx.coroutines.runBlocking

class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>(), OnProgressBarListener {

    private lateinit var timer: Timer

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    override fun onProgressChange(current: Int, max: Int) {
        if (current == max) {
            binding.numberProgressBar.progress = 0
        }
    }

    override val bindingInflater: (LayoutInflater) -> ActivitySplashScreenBinding
        get() = ActivitySplashScreenBinding::inflate

    override fun setup(savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val endProgress = 3400
        val welcomeBack = object : Thread() {
            var startProgress = 0
            override fun run() {
                try {
                    super.run()
                    while (startProgress < endProgress) {
                        sleep(100)
                        startProgress += 100
                    }
                } catch (e: InterruptedException) {
                    println("Error di: $e")
                } finally {
                    finish()
                    val intentMain = Intent(this@SplashScreenActivity, MainActivity::class.java)
                    startActivity(intentMain)
                }
            }
        }
        welcomeBack.start()

        binding.numberProgressBar.setOnProgressBarListener(this)
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                runBlocking { binding.numberProgressBar.incrementProgressBy(1) }
            }
        }, 1000, 30)
    }
}
