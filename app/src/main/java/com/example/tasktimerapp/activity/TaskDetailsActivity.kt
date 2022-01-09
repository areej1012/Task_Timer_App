package com.example.tasktimerapp.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.ImageView
import android.widget.TextView
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
    private var stopTime : Long = 0
    private var isStop = true

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
                    stopTime = SystemClock.elapsedRealtime() - taskTimer.base
                } else {
                    isPlay = true
                    startStopButton.setBackgroundResource(R.drawable.push_gra_icon)
                    spinKit.isVisible = true
                    if (task.total_Time > 0 && !isStop ){
                        taskTimer.base = SystemClock.elapsedRealtime() - task.total_Time
                    } else if (isStop) {
                        taskTimer.base = SystemClock.elapsedRealtime() - stopTime
                        isStop = false
                    } else {
                        taskTimer.base = SystemClock.elapsedRealtime() - 0
                    }
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
                task.total_Time = stopTime
                save(task)
                resetTimer()
            }

            taskDescriptionButton.setOnClickListener {
                alert()
            }


        }


    }

    private fun setUI() {
        setFullScreen(window)
        lightStatueBar(window)
        task = intent.getSerializableExtra("task") as Task
        binding.apply {
            taskTitle.text = task.title
            if (task.total_Time > 0 ) {
                taskTimer.base = SystemClock.elapsedRealtime() - task.total_Time
            } else {
                taskTimer.base = SystemClock.elapsedRealtime() - 0
            }
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

    private fun alert() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.task_description_card)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val taskTitle = dialog.findViewById<TextView>(R.id.task_title_alert)
        val taskDescription = dialog.findViewById<TextView>(R.id.task_detail_alert)
        val closeButton = dialog.findViewById<ImageView>(R.id.close_icon)

        taskTitle.text = task.title
        taskDescription.text = task.description
        dialog.show()

        closeButton.setOnClickListener {
            dialog.dismiss()
        }

    }

}