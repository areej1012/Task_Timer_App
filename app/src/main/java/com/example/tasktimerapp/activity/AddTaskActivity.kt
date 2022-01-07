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
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.setFullScreen
import com.example.tasktimerapp.R
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
                if (taskTitleEt.text.isNotEmpty() && taskDescriptionEt.text.isNotEmpty()) {

                    val taskTitle = taskTitleEt.text.toString()
                    val taskDescription = taskDescriptionEt.text.toString()
                    task = Task(0, taskTitle , taskDescription, 0)

                    TaskDatabase.getDatabase(applicationContext)
                    CoroutineScope(Dispatchers.IO).launch {
                        TaskDatabase.getDatabase(applicationContext).TaskDao().addTask(task)
                    }
                    alert("Success Add Task", true)
                    resetUI()
                } else {
                    alert("Please fill all blanks", false)
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

    private fun alert(message: String, isSuccess: Boolean) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_alert)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val successTextView = dialog.findViewById<TextView>(R.id.success_text)
        val successImageView = dialog.findViewById<ImageView>(R.id.success_image)
        val closeButton = dialog.findViewById<ImageView>(R.id.close_icon)

        if (!isSuccess) {
            successImageView.setImageResource(R.drawable.failed)
        }

        successTextView.text = message
        dialog.show()

        closeButton.setOnClickListener {
            dialog.dismiss()
        }

    }
}