package com.example.tasktimerapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.setFullScreen
import com.example.tasktimerapp.databinding.ActivityGetStartedBinding

class GetStartedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetStartedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()
       binding.getStartedButton.setOnClickListener{
           val intent = Intent(this,TaskListActivity::class.java)
           startActivity(intent)
       }

    }

    private fun setUI() {
        setFullScreen(window)
        lightStatueBar(window)
    }

}