package com.example.tasktimerapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.setFullScreen
import com.example.tasktimerapp.adapter.SummaryAdapter
import com.example.tasktimerapp.adapter.TaskAdapter
import com.example.tasktimerapp.database.Task
import com.example.tasktimerapp.database.TaskDatabase
import com.example.tasktimerapp.databinding.ActivitySummaryBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class SummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySummaryBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var summaryAdapter: SummaryAdapter
    private lateinit var tasks: ArrayList<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUI()

        CoroutineScope(Dispatchers.IO).launch {
            tasks =
                TaskDatabase.getDatabase(applicationContext).TaskDao().getTask() as ArrayList<Task>
            println("All Tasks")
            println(tasks)
            setRecyclerview()
        }

        binding.backButton.setOnClickListener {
            finish()
        }

    }

    private fun setUI() {
        setFullScreen(window)
        lightStatueBar(window)
    }

    private fun setRecyclerview() {
        recyclerView = binding.summaryRecyclerview
        summaryAdapter = SummaryAdapter(tasks, this)
        recyclerView.adapter = summaryAdapter
    }
}