package com.example.tasktimerapp.adapter

import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerapp.databinding.TaskCardBinding

class TaskAdapter(private val tasks: List<String>) :
    RecyclerView.Adapter<TaskAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(var binding: TaskCardBinding) : RecyclerView.ViewHolder(binding.root)

    var isPlay = false
    var stopTime: Long = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            TaskCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val taskItem = tasks[position]
        holder.binding.apply {

            timeButton.setOnClickListener {
                if (!isPlay) {
                    pushTimerButton.isVisible = true
                    taskTimer.isVisible = true
                    taskDescription.isVisible = false
                    editButton.isVisible = false
                    deleteButton.isVisible = false
                    timeButton.isVisible = false
                    isPlay = true
                    taskTimer.base = SystemClock.elapsedRealtime() - stopTime
                    taskTimer.start()
                    pushTimerButton.setOnClickListener {
                        taskTimer.stop()
                        stopTime = SystemClock.elapsedRealtime() - taskTimer.base
                        isPlay = false
                        Log.e("stoptime", stopTime.toString())
                    }

                }
            }

        }
    }


    override fun getItemCount() = tasks.size
}