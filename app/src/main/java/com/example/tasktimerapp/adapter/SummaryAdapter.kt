package com.example.tasktimerapp.adapter

import android.content.Context
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerapp.database.Task
import com.example.tasktimerapp.databinding.SummaryCardBinding
import com.example.tasktimerapp.databinding.TaskCardBinding

class SummaryAdapter(private val tasks: ArrayList<Task>, val context: Context) :
    RecyclerView.Adapter<SummaryAdapter.SummaryHoldr>() {

    class SummaryHoldr(var binding: SummaryCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryHoldr {
        return SummaryHoldr(
            SummaryCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SummaryHoldr, position: Int) {
        val task = tasks[position]
        holder.binding.apply {
            taskTitleSummary.text = task.title
            taskTimerSummary.base = SystemClock.elapsedRealtime() - task.total_Time
        }
    }

    override fun getItemCount() = tasks.size
}