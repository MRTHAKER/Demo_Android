     package com.yudiz.demo.navigation.intent_filter

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yudiz.demo.databinding.ActivityFilterTextBinding

class FilterTextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityFilterTextBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.extras?.let {
            binding.filterTv.text=it.getString(Intent.EXTRA_TEXT)
            Toast.makeText(this,intent.getStringExtra(Intent.EXTRA_TEXT),Toast.LENGTH_SHORT).show()
        }


    }
}