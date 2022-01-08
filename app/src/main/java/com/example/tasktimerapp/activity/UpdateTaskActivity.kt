package com.example.tasktimerapp.activity

import android.annotation.SuppressLint
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

                    alert("Success Updated",true)

                } else {
                    alert("Pleases Fill all blanks", false)
                }
            }

            resetButton.setOnClickListener {
                task.total_Time = 0
                taskTime.setText("Time taken: ${convertLongToTime(task.total_Time)}")
            }

            backButton.setOnClickListener {
                finish()
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