package com.yudiz.demo.navigation.services

import android.app.IntentService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.*
import android.util.Log
import com.yudiz.demo.R
import java.util.*
import kotlin.properties.Delegates


class BackgroundService : IntentService("Timer") {
    lateinit var time: String
    var startTime = false
    var resultReceiver: ResultReceiver? = null
    var seconds = 0
    var hours by Delegates.notNull<Int>()
    var minutes by Delegates.notNull<Int>()
    var secs by Delegates.notNull<Int>()
    lateinit var bundle: Bundle
    lateinit var pauseBroadcast: PauseBroadcast

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    override fun onHandleIntent(intent: Intent?) {
        pauseBroadcast = PauseBroadcast()
        val filters = IntentFilter()
        filters.apply {
            addAction(getString(R.string.pause))
        }
        registerReceiver(pauseBroadcast, filters)
        Log.d("Service", "Service started")
        startTime = true
        intent?.apply {
            resultReceiver = getParcelableExtra(getString(R.string.handler))
            seconds = getIntExtra(getString(R.string.seconds), 0)
            hours = getIntExtra(getString(R.string.hours), 0)
            minutes = getIntExtra(getString(R.string.minutes), 0)
            secs = getIntExtra(getString(R.string.secs), 0)
        }
        bundle = Bundle()
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
            Log.d("Time", time)
            bundle.putString(getString(R.string.message), time)
            resultReceiver?.send(Status.RUNNING.currentStatus, bundle)
            Thread.sleep(1000)
        }
    }

    override fun onDestroy() {
        startTime = false
        unregisterReceiver(pauseBroadcast)
    }

    inner class PauseBroadcast : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                getString(R.string.pause) -> {
                    startTime = false
                    bundle.apply {
                        putInt(getString(R.string.seconds), seconds)
                        putInt(getString(R.string.hours), hours)
                        putInt(getString(R.string.minutes), minutes)
                        putInt(getString(R.string.secs), secs)
                    }
                    resultReceiver?.send(Status.PAUSE.currentStatus, bundle)
                }
            }
        }

    }
}
