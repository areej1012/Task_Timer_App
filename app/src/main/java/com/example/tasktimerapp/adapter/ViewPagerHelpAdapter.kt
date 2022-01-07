package com.example.tasktimerapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.tasktimerapp.fragment.*

class ViewPagerHelpAdapter (fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = listOf(
        Help1Fragment(),
        Help2Fragment(),
        Help3Fragment()
    )

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }
}