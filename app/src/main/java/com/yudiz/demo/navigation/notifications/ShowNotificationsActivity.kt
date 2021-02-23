package com.yudiz.demo.navigation.notifications

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yudiz.demo.R
import com.yudiz.demo.services.ForegroundService
import com.yudiz.demo.services.MediaPlayerService

class ShowNotificationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_notifications)
        if (intent.action.equals(getString(R.string.stop))){
            stopService(Intent(applicationContext,ForegroundService::class.java))
            finish()
        }
        else if(intent.action.equals(getString(R.string.stopMedia))){
            stopService(Intent(applicationContext,MediaPlayerService::class.java).apply { action=getString(R.string.stopMedia) })
            finish()
        }
        else {
            Toast.makeText(this, "You have been notified!", Toast.LENGTH_SHORT).show()
        }
    }
}