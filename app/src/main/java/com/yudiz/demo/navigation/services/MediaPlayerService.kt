package com.yudiz.demo.navigation.services

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import java.util.*

class MediaPlayerService : Service(), MediaPlayer.OnPreparedListener {
    private var player: MediaPlayer? = null
    override fun onBind(intent: Intent): IBinder? = null
    lateinit var t: Thread
    override fun onCreate() {
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        t = Thread()
        val uri: Uri? = intent?.getParcelableExtra("uri")
        t.run {
            when (intent?.action) {
                "play" -> {
                    if (player == null) {
                        initPlayer()
                        uri?.let { player?.setDataSource(applicationContext, it) }
                        player?.setOnPreparedListener(this@MediaPlayerService)
                        player?.prepareAsync()
                    } else {
                        player?.start()
                    }
                }
                "pause" -> {
                    player?.pause()
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
}