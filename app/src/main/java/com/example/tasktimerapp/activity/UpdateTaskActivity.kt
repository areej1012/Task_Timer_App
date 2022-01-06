package com.example.tasktimerapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.setFullScreen
import com.example.tasktimerapp.database.Task
import com.example.tasktimerapp.database.TaskDatabase
import com.example.tasktimerapp.databinding.ActivityUpdateTaskBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var task: Task

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUI()

        binding.apply {

            taskTitleEt.setText(task.title)
            taskDescriptionEt.setText(task.description)
            taskTime.setText("Time taken: ${convertLongToTime(task.total_Time)}")

            updateTaskButton.setOnClickListener {
                if (taskTitleEt.text.isNotEmpty() || taskDescriptionEt.text.isNotEmpty()) {

                    task.title = taskTitleEt.text.toString()
                    task.description = taskDescriptionEt.text.toString()

                    TaskDatabase.getDatabase(applicationContext)
                    CoroutineScope(Dispatchers.IO).launch {
                        TaskDatabase.getDatabase(applicationContext).TaskDao().updateTask(task)
                    }

                }
            }

            resetButton.setOnClickListener {
                task.total_Time = 0
                taskTime.setText("Time taken: ${convertLongToTime(task.total_Time)}")
            }

            backButton.setOnClickListener {
                val intent = Intent(this@UpdateTaskActivity, TaskListActivity::class.java)
                startActivity(intent)
            }

        }


    }

    private fun setUI() {
        setFullScreen(window)
        lightStatueBar(window)
        task = intent.getSerializableExtra("task") as Task
    }

    @SuppressLint("SimpleDateFormat")
    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("mm:ss")
        return format.format(date)
    }

}