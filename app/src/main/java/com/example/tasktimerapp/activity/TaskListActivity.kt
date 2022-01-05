package com.example.tasktimerapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.setFullScreen
import com.example.tasktimerapp.adapter.TaskAdapter
import com.example.tasktimerapp.databinding.ActivityTaskListBinding

class TaskListActivity : AppCompatActivity() {


    private lateinit var binding: ActivityTaskListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var tasks: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUI()

        tasks = arrayListOf("arwa","","","","","","","","","",)
        setRecyclerview()

        binding.bttnClick.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUI() {
        setFullScreen(window)
        lightStatueBar(window)
    }
    private fun setRecyclerview() {
        recyclerView = binding.taskRecyclerview
        taskAdapter = TaskAdapter(tasks)
        recyclerView.adapter = taskAdapter
    }
}