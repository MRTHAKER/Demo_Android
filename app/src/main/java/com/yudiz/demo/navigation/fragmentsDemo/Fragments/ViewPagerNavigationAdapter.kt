package com.yudiz.demo.navigation.fragmentsDemo.Fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.yudiz.demo.layout.viewpager.fragments.AFragment
import com.yudiz.demo.layout.viewpager.fragments.BFragment
import com.yudiz.demo.layout.viewpager.fragments.CFragment

class ViewPagerNavigationAdapter(private val fm: FragmentManager, private val behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {
    override fun getCount(): Int = behavior

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> TasksFragment()
            2 -> SettingsFragment()
            else -> HomeFragment()
        }
    }
}