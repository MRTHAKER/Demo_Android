package com.yudiz.demo.services

import android.app.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yudiz.demo.databinding.ActivityForegroundBinding

class ForegroundActivity : AppCompatActivity(){
    lateinit var binding: ActivityForegroundBinding
    lateinit var serviceIntent:Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityForegroundBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        serviceIntent= Intent(this,ForegroundService::class.java)
        binding.btnForegroundStart.setOnClickListener{
            startService(serviceIntent)
        }
    }
}