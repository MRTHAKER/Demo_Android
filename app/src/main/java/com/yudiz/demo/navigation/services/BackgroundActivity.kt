package com.yudiz.demo.navigation.services

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityBackgroundBinding
import kotlin.properties.Delegates

class BackgroundActivity : AppCompatActivity(), View.OnClickListener,
    CustomResultReceiver.AppReceiver {
    lateinit var binding: ActivityBackgroundBinding
    lateinit var serviceIntent: Intent
    lateinit var customResultReceiver: CustomResultReceiver
    lateinit var pauseBroadcast: Intent
    private var seconds = 0
    var hours = seconds / 3600
    var minutes = seconds % 3600 / 60
    var secs = seconds % 60
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBackgroundBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        serviceIntent = Intent(applicationContext, BackgroundService::class.java)
        pauseBroadcast = Intent().apply { action = getString(R.string.pause) }
        try {
            customResultReceiver = CustomResultReceiver(Handler(), this)
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
        }
        binding.btnBackgroundPause.setOnClickListener(this)
        binding.btnBackgroundStop.setOnClickListener(this)
        binding.btnBackgroundStart.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnBackgroundStart -> {
                serviceIntent.apply {
                    putExtra(getString(R.string.handler), customResultReceiver)
                    putExtra(getString(R.string.seconds), seconds)
                    putExtra(getString(R.string.hours), hours)
                    putExtra(getString(R.string.minutes), minutes)
                    putExtra(getString(R.string.secs), secs)
                }
                startService(serviceIntent)
            }
            binding.btnBackgroundPause -> {
                sendBroadcast(pauseBroadcast)
            }
            binding.btnBackgroundStop -> {
                stopService(serviceIntent)
                binding.backgroundTimerTv.text = getString(R.string.default_timer)
                seconds = 0
            }
        }
    }

    override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
        when (resultCode) {
            Status.PAUSE.currentStatus -> {
                binding.backgroundTimerTv.text = resultData.getString(getString(R.string.message))
                resultData.apply {
                    secs = getInt(getString(R.string.secs))
                    hours = getInt(getString(R.string.hours))
                    minutes = getInt(getString(R.string.minutes))
                    seconds = getInt(getString(R.string.seconds))
                }
            }
            Status.RUNNING.currentStatus -> {
                binding.backgroundTimerTv.text = resultData.getString(getString(R.string.message))
            }
        }

    }
}
