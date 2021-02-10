package com.yudiz.demo.navigation.notifications

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yudiz.demo.R

class ShowNotificationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_notifications)
        Toast.makeText(this,"You have been notified!",Toast.LENGTH_SHORT).show()
    }
}