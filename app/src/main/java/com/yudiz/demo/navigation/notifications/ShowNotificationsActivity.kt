package com.yudiz.demo.navigation.notifications

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yudiz.demo.R
import com.yudiz.demo.navigation.services.ForegroundService

class ShowNotificationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_notifications)
        if (intent.action.equals(getString(R.string.stop))){
            stopService(Intent(applicationContext,ForegroundService::class.java))
            finish()
        }else {
            Toast.makeText(this, "You have been notified!", Toast.LENGTH_SHORT).show()
        }
    }
}