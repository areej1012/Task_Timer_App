package com.example.tasktimerapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.setFullScreen
import com.example.tasktimerapp.databinding.ActivityTaskDetailsBinding
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.github.ybq.android.spinkit.sprite.Sprite


class TaskDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()

        var progressBar = binding.spinKit
        var doubleBounce: Sprite = DoubleBounce()
        progressBar.setIndeterminateDrawable(doubleBounce)
    }

    private fun setUI() {
        setFullScreen(window)
        lightStatueBar(window)
    }
}