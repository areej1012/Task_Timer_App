package com.example.tasktimerapp.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerapp.R
import com.example.tasktimerapp.activity.TaskDetailsActivity
import com.example.tasktimerapp.activity.TaskListActivity
import com.example.tasktimerapp.database.Task
import com.example.tasktimerapp.databinding.TaskCardBinding


class TaskAdapter(private val tasks: List<String>, val context: Context) :
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

        holder.binding.apply {

            // Go to task detailes
            taskCardContainer.setOnClickListener {
                val intent = Intent(context, TaskDetailsActivity::class.java)
                context.startActivity(intent)
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


}


//override fun onBindViewHolder(holder: TaskAdapter.ItemViewHolder, position: Int) {
//    val taskItem = tasks[position]
//
//    holder.binding.apply {
//
//        timeButton.setOnClickListener {
//            isPlay = true
//            if (isPlay) {
//                changeVisible(true, holder.binding)
//                bindingTask = holder.binding
//                taskTimer.base = SystemClock.elapsedRealtime() - stopTime
//                taskTimer.start()
//                startStopButton.setOnClickListener {
//                    taskTimer.stop()
//                    stopTime = SystemClock.elapsedRealtime() - taskTimer.base
//                    isPlay = false
//                    Log.e("stoptime", stopTime.toString())
//                    stopTime = 0
//
//                }
//
//            }
////                else {
////                    //old
////                    bindingTask?.taskTimer!!.stop()
////                    stopTime = SystemClock.elapsedRealtime() - bindingTask?.taskTimer!!.base
////
////                    changeVisible(false, bindingTask!!)
////
////                    // save stop time
////                    bindingTask = holder.binding
////                    stopTime = 0
////
////                    //new
////                    startStopButton.isVisible = true
////                    taskTimer.isVisible = true
////                    taskDescription.isVisible = false
////                    editButton.isVisible = false
////                    deleteButton.isVisible = false
////                    timeButton.isVisible = false
////                    taskTimer.base = SystemClock.elapsedRealtime() - stopTime
////                    taskTimer.start()
////                    startStopButton.setOnClickListener {
////                        taskTimer.stop()
////                        stopTime = SystemClock.elapsedRealtime() - taskTimer.base
////                        isPlay = false
////                        Log.e("stoptime", stopTime.toString())
////                        stopTime = 0
////
////                    }
////                }
//        }
//
//    }
//}
