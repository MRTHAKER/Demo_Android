package com.yudiz.demo.navigation.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.os.ResultReceiver
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.yudiz.demo.R
import com.yudiz.demo.navigation.notifications.ShowNotificationsActivity
import java.util.*
import kotlin.properties.Delegates

class ForegroundService : IntentService("ForegroundService") {
    private lateinit var time: String
    private var startTime = false
    private val notifyID=101
    private lateinit var builder:NotificationCompat.Builder
    private var seconds = 0
    private var hours by Delegates.notNull<Int>()
    private var minutes by Delegates.notNull<Int>()
    private var secs by Delegates.notNull<Int>()

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onHandleIntent(intent: Intent?) {
        startTime = true
        while (startTime) {
            Log.d("Service", "Handle intent")
            hours = seconds / 3600
            minutes = seconds % 3600 / 60
            secs = seconds % 60
            seconds++
            time = String.format(
                Locale.getDefault(),
                getString(R.string.default_watch_format),
                hours,
                minutes,
                secs
            )
            sendNotification()
            builder.setContentText(time)
            startForeground(notifyID,builder.build())
            Log.d("Time", time)
            Thread.sleep(1000)

        }
    }

    override fun onDestroy() {
        Toast.makeText(applicationContext,"Service Stopped",Toast.LENGTH_SHORT).show()
    }
    private fun sendNotification(){
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
        val intent = Intent(this, ShowNotificationsActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            action=getString(R.string.stop)
            putExtra(getString(R.string.id),notifyID)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        builder = NotificationCompat.Builder(this, getString(R.string.channel_test_id))
            .setSmallIcon(R.drawable.ic_clear_black_18)
            .setContentTitle("Timer")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.ic_clear_24, getString(R.string.stop), pendingIntent)
    }
}