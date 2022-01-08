package com.example.tasktimerapp.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.setFullScreen
import com.example.tasktimerapp.R
import com.example.tasktimerapp.adapter.ViewPagerAdapter
import com.example.tasktimerapp.databinding.ActivityGetStartedBinding

class GetStartedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetStartedBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var isFirst: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()
        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)

        sharedPreferences = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        checkIsFirstTime()

        binding.skip.setOnClickListener {
            val intent = Intent(this, TaskListActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun checkIsFirstTime() {
        isFirst = sharedPreferences.getBoolean("is_first", true)
        if (!isFirst) {
            with(sharedPreferences.edit()) {
                putBoolean("is_first", true)
                apply()
            }
            val intent = Intent(this, TaskListActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            with(sharedPreferences.edit()) {
                putBoolean("is_first", false)
                apply()
            }
        }
    }

    private fun setUI() {
        setFullScreen(window)
        lightStatueBar(window)
    }

}