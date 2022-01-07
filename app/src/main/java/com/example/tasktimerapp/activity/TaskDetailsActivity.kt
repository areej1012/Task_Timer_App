package com.example.tasktimerapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.setFullScreen
import com.example.tasktimerapp.R
import com.example.tasktimerapp.database.Task
import com.example.tasktimerapp.database.TaskDatabase
import com.example.tasktimerapp.databinding.ActivityTaskDetailsBinding
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.github.ybq.android.spinkit.sprite.Sprite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TaskDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskDetailsBinding
    private lateinit var task: Task

    private var isPlay = false
    private var stopTime = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()

        println("Task details= $task")

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
                if (isPlay) {
                    isPlay = false
                    startStopButton.setBackgroundResource(R.drawable.start_gra_icon)
                    spinKit.isVisible = false
                    taskTimer.stop()
                } else {
                    isPlay = true
                    startStopButton.setBackgroundResource(R.drawable.push_gra_icon)
                    spinKit.isVisible = true
                    taskTimer.start()
                }
            }

            restartTimerButton.setOnClickListener {
                resetTimer()
                taskTimer.stop()
                taskTimer.base = SystemClock.elapsedRealtime() - 0
                task.total_Time = 0
                save(task)
            }
            doneButton.setOnClickListener {

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
            taskTimer.base = SystemClock.elapsedRealtime() - task.total_Time
        }
    }

    private fun resetTimer() {
        stopTime = 0
        isPlay = false
    }

    private fun save(task: Task) {
        TaskDatabase.getDatabase(applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            TaskDatabase.getDatabase(applicationContext).TaskDao().updateTask(task)
        }
        Toast.makeText(this, "Task Timer Successfully Updated", Toast.LENGTH_SHORT).show()
        println("Task Save: $task")
    }

}