package com.yudiz.demo.saving_multimedia.multithreading

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityAsyncBinding

@Suppress("DEPRECATION")
class AsyncActivity : AppCompatActivity() {
    lateinit var binding: ActivityAsyncBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAsyncBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnStartAsync.setOnClickListener {
            Mytask().execute()
        }
    }

    inner class Mytask : AsyncTask<Unit, Int, Unit>() {
        override fun onPreExecute() {
            super.onPreExecute()
            Toast.makeText(this@AsyncActivity, "Execution started", Toast.LENGTH_SHORT).show()
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            binding.tvAsyncCounter.text = values[0].toString()
        }

        override fun onCancelled(result: Unit?) {
            super.onCancelled(result)
            Toast.makeText(this@AsyncActivity, "Execution stopped", Toast.LENGTH_SHORT).show()

        }

        override fun doInBackground(vararg params: Unit?) {
            for (i in 1..10) {
                if (i == 10) {
                    publishProgress(i)
                    cancel(true)
                }
                publishProgress(i)
                Thread.sleep(1000)
            }
        }

    }
}