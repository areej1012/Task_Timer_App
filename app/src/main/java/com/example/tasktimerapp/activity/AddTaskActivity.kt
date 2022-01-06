package com.example.tasktimerapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.setFullScreen
import com.example.tasktimerapp.database.Task
import com.example.tasktimerapp.database.TaskDatabase
import com.example.tasktimerapp.databinding.ActivityAddTaskBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()

        binding.apply {

            addTaskButton.setOnClickListener {
                if (taskTitleEt.text.isNotEmpty() || taskDescriptionEt.text.isNotEmpty()) {

                    val taskTitle = taskTitleEt.text.toString()
                    val taskDescription = taskDescriptionEt.text.toString()
                    task = Task(0, taskTitle , taskDescription, SystemClock.elapsedRealtime()-0)

                    TaskDatabase.getDatabase(applicationContext)
                    CoroutineScope(Dispatchers.IO).launch {
                        TaskDatabase.getDatabase(applicationContext).TaskDao().addTask(task)
                    }

                    resetUI()
                }
            }

            backButton.setOnClickListener {
                val intent = Intent(this@AddTaskActivity, TaskListActivity::class.java)
                startActivity(intent)
            }

        }


    }

    private fun setUI() {
        setFullScreen(window)
        lightStatueBar(window)
    }

    private fun resetUI() {
        binding.taskTitleEt.text = null
        binding.taskDescriptionEt.text = null
    }
}