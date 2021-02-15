package com.yudiz.demo.navigation.broadcast_receiver

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityBroadCastReceiverBinding

class BroadCastReceiverActivity : AppCompatActivity() {
    lateinit var usbReceiver:UsbReceiver
    lateinit var textReceiver:TextReceiver
    lateinit var networkBroadcast:NetworkReceiver
    var message:String="text message"
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding=ActivityBroadCastReceiverBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        usbReceiver= UsbReceiver()
        textReceiver= TextReceiver()
        networkBroadcast=NetworkReceiver()
        binding.btnSendBroadcast.setOnClickListener {
            Intent().also { intent ->
                intent.action = "text message"
                intent.putExtra("message", binding.edBroadcast.text.toString())
                sendBroadcast(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(usbReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        registerReceiver(textReceiver, IntentFilter(message))
        registerReceiver(networkBroadcast, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(usbReceiver)
        unregisterReceiver(textReceiver)
        unregisterReceiver(networkBroadcast)
    }
}
