package com.example.tasktimerapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.setFullScreen
import com.example.tasktimerapp.R
import com.example.tasktimerapp.adapter.TaskAdapter
import com.example.tasktimerapp.database.Task
import com.example.tasktimerapp.database.TaskDatabase
import com.example.tasktimerapp.databinding.ActivityTaskListBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class TaskListActivity : AppCompatActivity() {


    private lateinit var binding: ActivityTaskListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskString: ArrayList<String>
    private lateinit var tasks: ArrayList<Task>
    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigationDrawer()
        setUI()

        CoroutineScope(Dispatchers.IO).launch {
            tasks =
                TaskDatabase.getDatabase(applicationContext).TaskDao().getTask() as ArrayList<Task>
            println("All Tasks")
            println(tasks)
            if (tasks.size != 0 ) {
                binding.noTaskId.isVisible = false
            }
            setRecyclerview()

        }


        binding.bttnClick.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }

        binding.menuButton.setOnClickListener {
            binding.drawerLayout.openDrawer(Gravity.LEFT)
        }


    }

    private fun setUI() {
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setFullScreen(window)
        lightStatueBar(window)
    }

    private fun setRecyclerview() {
        recyclerView = binding.taskRecyclerview
        taskAdapter = TaskAdapter(tasks, this)
        recyclerView.adapter = taskAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    fun initNavigationDrawer() {
        val navigationView = binding.navView
        navigationView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener {
            menuItem ->
            val id = menuItem.itemId
            when(id){
                R.id.home ->
                    startActivity(Intent(this, TaskListActivity::class.java))
                R.id.summary ->
                    startActivity(Intent(this, SummaryActivity::class.java))

                R.id.help ->
                    startActivity(Intent(this, HelpActivity::class.java))
            }
            return@OnNavigationItemSelectedListener true
        }
        )
    }

}