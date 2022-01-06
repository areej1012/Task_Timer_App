package com.example.tasktimerapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.setFullScreen
import com.example.tasktimerapp.databinding.ActivityAddTaskBinding

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
//Shuaa
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()
    }

    private fun setUI() {
        setFullScreen(window)
        lightStatueBar(window)
    }
}