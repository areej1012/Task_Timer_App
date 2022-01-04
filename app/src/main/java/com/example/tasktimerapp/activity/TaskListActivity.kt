package com.example.tasktimerapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasktimerapp.adapter.TaskAdapter
import com.example.tasktimerapp.databinding.ActivityTaskListBinding

class TaskListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskListBinding
    lateinit var tasks: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tasks = arrayListOf()

        binding.bttnClick.setOnClickListener {
            if (binding.editTxt.text != null){
                tasks.add(binding.editTxt.text.toString())
                binding.editTxt.text = null
            }
            else{
                Toast.makeText(this, "Enter something ", Toast.LENGTH_LONG).show()
            }
        }
        binding.mainRV.adapter = TaskAdapter(tasks)
        binding.mainRV.layoutManager = LinearLayoutManager(this)
    }
}