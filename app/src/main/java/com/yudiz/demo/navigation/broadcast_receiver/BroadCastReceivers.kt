package com.yudiz.demo.navigation.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.BatteryManager
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat

class UsbReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val chargePlug: Int = intent?.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) ?: -1
        Toast.makeText(
            context, when (chargePlug) {
                BatteryManager.BATTERY_PLUGGED_AC -> "Device is Charging"
                BatteryManager.BATTERY_PLUGGED_USB -> "Usb plugged In"
                else -> "Device disconnected"
            }, Toast.LENGTH_SHORT
        ).show()
    }
}
class TextReceiver: BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,intent?.getStringExtra("message"),Toast.LENGTH_SHORT).show()
    }

}
class NetworkReceiver:BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
      val manager= context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = manager.activeNetworkInfo
        if(activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting) {
           Toast.makeText(context,"Connected", Toast.LENGTH_SHORT).show()
       }
        else{
            Toast.makeText(context,"Disconnected",Toast.LENGTH_SHORT).show()
        }


    }

}

