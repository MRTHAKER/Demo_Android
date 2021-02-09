     package com.yudiz.demo.navigation.IntentFilter

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
        val extra =intent.extras
        if (extra != null) {
            binding.filterTv.text=extra.getString(Intent.EXTRA_TEXT)
            Toast.makeText(this,intent.getStringExtra(Intent.EXTRA_TEXT),Toast.LENGTH_SHORT).show()
        }
    }
}