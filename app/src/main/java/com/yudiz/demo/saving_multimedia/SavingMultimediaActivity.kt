package com.yudiz.demo.saving_multimedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yudiz.demo.databinding.ActivitySavingMultimediaBinding
import com.yudiz.demo.saving_multimedia.multithreading.AsyncActivity
import com.yudiz.demo.saving_multimedia.multithreading.CoroutineActivity
import com.yudiz.demo.saving_multimedia.multithreading.ThreadingOperationActivity
import com.yudiz.demo.saving_multimedia.multithreading.ThreadingSumActivity
import com.yudiz.demo.saving_multimedia.saving.SavingFileActivity

class SavingMultimediaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySavingMultimediaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.savingDataMultimediaMultithreading.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ThreadingSumActivity::class.java
                )
            )
        }
        binding.savingDataMultimediaMultithreadingFactorial.setOnClickListener {
            startActivity(
                Intent(this, ThreadingOperationActivity::class.java)
            )
        }
        binding.savingDataMultimediaMultithreadingAsync.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    AsyncActivity::class.java
                )
            )
        }
        binding.savingDataMultimediaMultithreadingCoroutine.setOnClickListener {
            startActivity(
                Intent(this, CoroutineActivity::class.java)
            )
        }
        binding.savingDataMultimediaSavingFile.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SavingFileActivity::class.java
                )
            )
        }
    }
}