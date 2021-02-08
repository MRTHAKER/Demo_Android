package com.yudiz.demo.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {
    lateinit var binding: ActivityNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navTopicsIntent.setOnClickListener { startActivity(Intent(this,IntentOneActivity::class.java)) }
        binding.navTopicsIntentFilters.setOnClickListener{startActivity(Intent(this,FilterMainActivity::class.java))}

    }
}