package com.yudiz.demo.navigation.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityBackgroundBinding
import com.yudiz.demo.databinding.ActivityForegroundBinding
import com.yudiz.demo.databinding.ActivityIntentOneBinding
import com.yudiz.demo.navigation.notifications.ShowNotificationsActivity

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