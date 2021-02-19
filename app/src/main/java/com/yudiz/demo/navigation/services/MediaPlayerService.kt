package com.yudiz.demo.navigation.services

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.ResultReceiver
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.yudiz.demo.R
import com.yudiz.demo.navigation.notifications.ShowNotificationsActivity
import java.text.FieldPosition
import java.util.*

class MediaPlayerService : Service(), MediaPlayer.OnPreparedListener,
    MediaPlayer.OnCompletionListener {
    private var player: MediaPlayer? = null
    override fun onBind(intent: Intent): IBinder? = null
    lateinit var t: Thread
    val notifyID = 112
    lateinit var name: String
    lateinit var mediacast:Intent
    private lateinit var builder: NotificationCompat.Builder
    override fun onCreate() {
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        t = Thread()
        when (intent?.action){
            getString(R.string.playMedia)->{
                player?.start()
                mediacast=Intent().apply {action=getString(R.string.playMedia)}
                sendBroadcast(mediacast)
            }
            getString(R.string.pauseMedia)->{
                player?.pause()
                mediacast=Intent().apply { action=getString(R.string.pauseMedia) }
                sendBroadcast(mediacast)
            }
            getString(R.string.stopMedia)->{
                player?.stop()
                mediacast=Intent().apply { action=getString(R.string.stopMedia) }
                sendBroadcast(mediacast)
            }
        }

        val uri: Uri? = intent?.getParcelableExtra("uri")
        name = intent?.getStringExtra("song").toString()
        t.run {
            startForeground(notifyID, sendNotification())
            when (intent?.action) {
                "play" -> {
                    if (player == null) {
                        initPlayer()
                        uri?.let { player?.setDataSource(applicationContext, it) }
                        player?.setOnPreparedListener(this@MediaPlayerService)
                        player?.setOnCompletionListener(this@MediaPlayerService)
                        player?.prepareAsync()
                    } else {
                        player?.start()
                    }
                }
                "pause" -> {
                    player?.pause()
                }
                "stop" -> {
                }
                else -> {
                }
            }
        }
        t.start()

        return START_STICKY
    }

    override fun onDestroy() {
        player?.release()
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }

    private fun initPlayer() {
        player = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
        }
    }

    private fun sendNotification(): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                getString(R.string.channel_test_id),
                getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_LOW
            )
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        val pauseIntent = Intent(this, MediaPlayerService::class.java).apply {
            action = getString(R.string.pauseMedia)
        }
        val pausePendingIntent: PendingIntent = PendingIntent.getService(this, 0, pauseIntent, 0)
        val playIntent = Intent(this, MediaPlayerService::class.java).apply {
            action=getString(R.string.playMedia)
        }
        val playPendingIntent: PendingIntent = PendingIntent.getService(this, 0, playIntent, 0)
        val stopIntent = Intent(this, MediaPlayerService::class.java).apply {
            action = getString(R.string.stopMedia)
        }
        val stopPendingIntent: PendingIntent = PendingIntent.getService(this, 0, stopIntent, 0)
        builder = NotificationCompat.Builder(this, getString(R.string.channel_test_id))
            .setSmallIcon(R.drawable.ic_music)
            .setContentTitle(getString(R.string.music_player))
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .addAction(R.drawable.ic_pause, getString(R.string.pause), pausePendingIntent)
            .addAction(R.drawable.ic_play, getString(R.string.play), playPendingIntent)
            .addAction(R.drawable.ic_stop, getString(R.string.stop), stopPendingIntent)
            .setContentText(name)
            .setAutoCancel(false)
        return builder.build()
    }

    override fun onCompletion(mp: MediaPlayer?) {
        mediacast=Intent().apply { action=getString(R.string.stopMedia) }
        sendBroadcast(mediacast)
        Toast.makeText(this,"Song completed",Toast.LENGTH_SHORT).show()
    }
}