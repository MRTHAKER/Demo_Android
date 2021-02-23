package com.yudiz.demo.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import com.yudiz.demo.databinding.ActivityIntentServiceBinding

class IntentServiceActivity : AppCompatActivity(), CustomResultReceiver.AppReceiver {
    lateinit var serviceIntent: Intent
    lateinit var customResultReceiver: CustomResultReceiver
    lateinit var binding: ActivityIntentServiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntentServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            customResultReceiver = CustomResultReceiver(Handler(), this)
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
        }
        serviceIntent = Intent(this, MyIntentService::class.java)
        binding.btnCompress.setOnClickListener {
            serviceIntent.putExtra("image", binding.ivUncompressed.drawable.toBitmap())
            serviceIntent.putExtra("handler",customResultReceiver)
            startService(serviceIntent)
        }

    }

    override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
        binding.ivCompressed.setImageBitmap(resultData.getParcelable("image"))
    }
}