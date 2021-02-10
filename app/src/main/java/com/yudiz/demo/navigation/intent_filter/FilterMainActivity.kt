package com.yudiz.demo.navigation.intent_filter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityFilterMainBinding

class FilterMainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityFilterMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.filterMainBtnTxt.setOnClickListener(this)
        binding.filterMainBtnImg.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.filterMainBtnImg -> {
                val sendImage = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_STREAM, Uri.parse(
                            "android.resource://" + packageName
                                    + "/drawable/" + "ic_android"
                        )
                    )
                    type = "image/*"
                }
                startActivity(Intent.createChooser(sendImage, getString(R.string.share_image)))
            }
            binding.filterMainBtnTxt -> {
                val sendText = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, binding.filterMainEt.text.toString())
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(sendText,getString(R.string.share_text)))
            }
        }
    }
}