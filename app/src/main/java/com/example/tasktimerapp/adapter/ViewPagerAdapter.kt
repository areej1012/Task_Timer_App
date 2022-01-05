package com.example.tasktimerapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.tasktimerapp.fragment.Slide1Fragment
import com.example.tasktimerapp.fragment.Slide2Fragment
import com.example.tasktimerapp.fragment.Slide3Fragment
import com.example.tasktimerapp.fragment.Slide4Fragment

class ViewPagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = listOf(
        Slide1Fragment(),
        Slide2Fragment(),
        Slide3Fragment(),
        Slide4Fragment()
    )
    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }
}