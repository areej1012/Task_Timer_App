package com.example.tasktimerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerapp.R
import com.example.tasktimerapp.databinding.TaskRowBinding
import kotlinx.android.synthetic.main.task_row.view.*

class TaskAdapter (private val tasks: List<String>): RecyclerView.Adapter<TaskAdapter.ItemViewHolder> () {
  inner  class ItemViewHolder(binding: TaskRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
           TaskRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun getItemCount() = tasks.size
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val guess = tasks[position]
        holder.itemView.apply {
            txtTask.text = guess
        }
    }
}