package com.example.tasktimerapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerapp.databinding.TaskCardBinding

class TaskAdapter(private val tasks: List<String>) :
    RecyclerView.Adapter<TaskAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(binding: TaskCardBinding) : RecyclerView.ViewHolder(binding.root)

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
        holder.itemView.apply {
        }
    }

    override fun getItemCount() = tasks.size
}