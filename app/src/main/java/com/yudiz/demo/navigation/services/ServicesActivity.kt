package com.yudiz.demo.navigation.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityServicesBinding

class ServicesActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityServicesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServicesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBackgroundService.setOnClickListener(this)
        binding.btnForegroundService.setOnClickListener(this)
        binding.btnIntentService.setOnClickListener(this)
        binding.btnMusicPlayer.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnBackgroundService -> {
                startActivity(Intent(this, BackgroundActivity::class.java))
            }
            binding.btnForegroundService -> {
                startActivity(Intent(this, ForegroundActivity::class.java))
            }
            binding.btnIntentService -> {
                startActivity(Intent(this, IntentServiceActivity::class.java))
            }
            binding.btnMusicPlayer -> {
                startActivity(Intent(this, MediaPlayerActivity::class.java))
            }
        }
    }
}