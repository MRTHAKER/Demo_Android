package com.yudiz.demo.saving_multimedia.multithreading

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.yudiz.demo.databinding.ActivityThreadingSumBinding

class ThreadingSumActivity : AppCompatActivity(),Handler.Callback {
    lateinit var binding: ActivityThreadingSumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThreadingSumBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val callback=Handler(this)
        binding.btnNumberSum.setOnClickListener{
        val t=Thread{
                val a: Int = binding.editTextNumberOne.text.toString().toInt()
                val b = binding.editTextNumberTwo.text.toString().toInt()
                val m=Message()
                m.what=a+b
                callback.sendMessage(m)
            }
            t.start()
        }
    }

    override fun handleMessage(msg: Message): Boolean {
        binding.numberThreadingTv.text= msg.what.toString()
        return true
    }
}