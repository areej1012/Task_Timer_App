package com.example.tasktimerapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {
    private val splashScreentimeot: kotlin.Long = 2500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.tasktimerapp.R.layout.activity_splash_screen)
        android.os.Handler().postDelayed({
            startActivity(android.content.Intent(this, GetStartedActivity::class.java))
            finish()
        }, splashScreentimeot)
    }
}