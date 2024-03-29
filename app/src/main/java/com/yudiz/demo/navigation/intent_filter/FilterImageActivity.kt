package com.yudiz.demo.navigation.intent_filter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityFilterImageBinding

class FilterImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFilterImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.action == Intent.ACTION_SEND && intent.type?.startsWith(getString(R.string.prefix_image)) == true) {
            binding.filterIv.setImageURI((intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri))
        }
    }
}