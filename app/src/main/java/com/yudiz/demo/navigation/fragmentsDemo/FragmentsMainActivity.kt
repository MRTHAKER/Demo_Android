package com.yudiz.demo.navigation.fragmentsDemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityFragmentsMainBinding
import com.yudiz.demo.navigation.fragmentsDemo.Fragments.HomeFragment
import com.yudiz.demo.navigation.fragmentsDemo.Fragments.SettingsFragment
import com.yudiz.demo.navigation.fragmentsDemo.Fragments.TasksFragment

class FragmentsMainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var binding: ActivityFragmentsMainBinding
    lateinit var home: HomeFragment
    lateinit var task: TasksFragment
    lateinit var settings: SettingsFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFragmentsMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        home = HomeFragment()
        task = TasksFragment()
        settings = SettingsFragment()
        supportFragmentManager.beginTransaction().add(binding.mainFrame.id, home).commit()
        binding.bottomMenu.setOnNavigationItemSelectedListener(this)
        supportActionBar?.title = "Home"
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_home -> {
                supportFragmentManager.beginTransaction().replace(binding.mainFrame.id, home)
                    .commit()
                supportActionBar?.title = "Home"
            }
            R.id.menu_task -> {
                supportFragmentManager.beginTransaction().replace(binding.mainFrame.id, task)
                    .commit()
                supportActionBar?.title = "Task"
            }
            R.id.menu_settings -> {
                supportFragmentManager.beginTransaction().replace(binding.mainFrame.id, settings)
                    .commit()
                supportActionBar?.title = "Settings"
            }
        }
        return true
    }

}