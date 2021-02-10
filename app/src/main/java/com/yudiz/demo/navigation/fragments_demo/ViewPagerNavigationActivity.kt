package com.yudiz.demo.navigation.fragments_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityViewPagerNavigationBinding
import com.yudiz.demo.navigation.fragments_demo.Fragments.ViewPagerNavigationAdapter

class ViewPagerNavigationActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    lateinit var binding: ActivityViewPagerNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityViewPagerNavigationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.mainPager.adapter =
            ViewPagerNavigationAdapter(supportFragmentManager, binding.bottomMenuPager.maxItemCount)
        binding.bottomMenuPager.setOnNavigationItemSelectedListener(this)
        binding.mainPager.addOnPageChangeListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_home -> {
                binding.mainPager.currentItem = 0
                supportActionBar?.title = "Home"
            }
            R.id.menu_task -> {
                binding.mainPager.currentItem = 1
                supportActionBar?.title = "Task"
            }
            R.id.menu_settings -> {
                binding.mainPager.currentItem = 2
                supportActionBar?.title = "Settings"
            }
        }
        return true

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> {
                binding.mainPager.currentItem = 0
                supportActionBar?.title = "Home"
                binding.bottomMenuPager.selectedItemId = R.id.menu_home
            }
            1 -> {
                binding.mainPager.currentItem = 1
                supportActionBar?.title = "Task"
                binding.bottomMenuPager.selectedItemId = R.id.menu_task
            }
            2 -> {
                binding.mainPager.currentItem = 2
                supportActionBar?.title = "Settings"
                binding.bottomMenuPager.selectedItemId = R.id.menu_settings
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
    }
}