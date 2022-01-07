package com.example.tasktimerapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.setFullScreen
import com.example.tasktimerapp.R
import com.example.tasktimerapp.databinding.ActivityHelpBinding

class HelpActivity : AppCompatActivity() {
    lateinit var binding: ActivityHelpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUI()

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun setUI() {
        setFullScreen(window)
        lightStatueBar(window)
    }
}