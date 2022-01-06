package com.example.tasktimerapp.adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerapp.R
import com.example.tasktimerapp.activity.TaskDetailsActivity
import com.example.tasktimerapp.activity.TaskListActivity
import com.example.tasktimerapp.activity.UpdateTaskActivity
import com.example.tasktimerapp.database.Task
import com.example.tasktimerapp.database.TaskDatabase
import com.example.tasktimerapp.databinding.TaskCardBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TaskAdapter(private val tasks: ArrayList<Task>, val context: Context) :
    RecyclerView.Adapter<TaskAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(var binding: TaskCardBinding) : RecyclerView.ViewHolder(binding.root)

    var isPlay = false
    var stopTime: Long = 0
    var bindingTask: TaskCardBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            TaskCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val taskItem = tasks[position]

        // UI Values
        holder.binding.apply {
            taskTitle.text = taskItem.title
            taskDescription.text = taskItem.description
            taskTimer.base = taskItem.total_Time
        }

        // UI Actions
        holder.binding.apply {

            // Go to task details
            taskCardContainer.setOnClickListener {
                val intent = Intent(context, TaskDetailsActivity::class.java)
                intent.putExtra("task", taskItem)
                context.startActivity(intent)
            }

            // Go to task edit
            editButton.setOnClickListener {
                val intent = Intent(context, UpdateTaskActivity::class.java)
                intent.putExtra("task", taskItem)
                context.startActivity(intent)
            }

            // Delete Button
            deleteButton.setOnClickListener {
                alert(taskItem)
            }

            // Timer Button
            timeButton.setOnClickListener {
                if (bindingTask != null) {
                    taskTimer.stop()
                    changeVisible(false, bindingTask!!)
                }
                changeVisible(true, holder.binding)
                bindingTask = holder.binding
                resetTimer()
            }

            // Done Button
            doneButton.setOnClickListener {
                changeVisible(false, holder.binding)
                resetTimer()
            }

            // Start - Stop Button
            startStopButton.setOnClickListener {
                if (isPlay) {
                    isPlay = false
                    taskTimer.stop()
                    stopTime = SystemClock.elapsedRealtime() - taskTimer.base

                } else {
                    isPlay = true
                    bindingTask = holder.binding
                    changeVisible(true, holder.binding)
                    startStopButton.setBackgroundResource(R.drawable.stop_icon)
                    taskTimer.base = SystemClock.elapsedRealtime() - stopTime
                    taskTimer.start()
                }

            }

            // Restart Button
            restartButton.setOnClickListener {
                resetTimer()
                taskTimer.stop()
                taskTimer.base = SystemClock.elapsedRealtime() - 0
            }

        }
    }

    private fun resetTimer() {
        stopTime = 0
        isPlay = false
    }

    private fun changeVisible(timerIsStart: Boolean, binding: TaskCardBinding) {
        binding.apply {
            startStopButton.isVisible = timerIsStart
            restartButton.isVisible = timerIsStart
            doneButton.isVisible = timerIsStart
            taskTimer.isVisible = timerIsStart
            taskDescription.isVisible = !timerIsStart
            editButton.isVisible = !timerIsStart
            deleteButton.isVisible = !timerIsStart
            timeButton.isVisible = !timerIsStart

            if (timerIsStart) {
                taskTitle.setTextColor(Color.WHITE)
                taskCardContainer.setBackgroundResource(R.drawable.gradient_background)
            } else {
                taskTitle.setTextColor(Color.BLACK)
                taskCardContainer.setBackgroundResource(R.color.card_bc)
            }
        }
    }

    private fun alert(task: Task) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.alert_button)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val closeButton = dialog.findViewById<ImageView>(R.id.close_icon)
        val yesButton = dialog.findViewById<Button>(R.id.yes_button)
        val cancelButton = dialog.findViewById<Button>(R.id.cancel_button)

        dialog.show()

        closeButton.setOnClickListener {
            dialog.dismiss()
        }
        yesButton.setOnClickListener {
            delete(task)
            dialog.dismiss()
        }
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun delete(task: Task) {
        TaskDatabase.getDatabase(context.applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            TaskDatabase.getDatabase(context.applicationContext).TaskDao().deleteTask(task)
        }
        tasks.remove(task)
        notifyDataSetChanged()
    }


}

