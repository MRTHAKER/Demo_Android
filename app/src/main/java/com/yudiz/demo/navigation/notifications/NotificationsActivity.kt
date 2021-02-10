package com.yudiz.demo.navigation.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityNotificationsBinding

class NotificationsActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityNotificationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnNotifySimple.setOnClickListener(this)
        binding.btnNotifyAction.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                getString(R.string.channel_test_id),
                getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        when (v) {
            binding.btnNotifySimple -> {
                val intent = Intent(this, ShowNotificationsActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
                val builder =
                    NotificationCompat.Builder(this, getString(R.string.channel_test_id))
                        .setSmallIcon(R.drawable.ic_clear_black_18)
                        .setContentTitle("Test Notification")
                        .setContentText("This is Test Notification")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)

                val managerCompat = NotificationManagerCompat.from(this)
                managerCompat.notify(111, builder.build())
                Toast.makeText(this, "Notified", Toast.LENGTH_SHORT).show()
            }
            binding.btnNotifyAction -> {
                val intent = Intent(this, ShowNotificationsActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
                val builder =
                    NotificationCompat.Builder(this, getString(R.string.channel_test_id))
                        .setSmallIcon(R.drawable.ic_clear_black_18)
                        .setContentTitle("Test Notification")
                        .setContentText("This is Test Notification")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .addAction(R.drawable.ic_clear_24, getString(R.string.ok), pendingIntent)
                val managerCompat = NotificationManagerCompat.from(this)
                managerCompat.notify(111, builder.build())
                Toast.makeText(this, "Notified", Toast.LENGTH_SHORT).show()
            }
        }
    }
}