package com.example.tasktimerapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.setFullScreen
import com.example.tasktimerapp.database.Task
import com.example.tasktimerapp.databinding.ActivityTaskDetailsBinding
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.github.ybq.android.spinkit.sprite.Sprite


class TaskDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskDetailsBinding
    private lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()

        binding.apply {
            editButton.setOnClickListener {
                val intent = Intent(this@TaskDetailsActivity, UpdateTaskActivity::class.java)
                intent.putExtra("task", task)
                startActivity(intent)
            }
            backButton.setOnClickListener {
                val intent = Intent(this@TaskDetailsActivity, TaskListActivity::class.java)
                startActivity(intent)
            }
            startStopButton.setOnClickListener {
                spinKit.isVisible = true
                taskTimer.start()
            }
        }


    }

    private fun setUI() {
        setFullScreen(window)
        lightStatueBar(window)
        task = intent.getSerializableExtra("task") as Task
        binding.apply {
            taskTitle.text = task.title
            taskDescription.text = task.description
            taskTimer.base = task.total_Time
        }
    }
}