package com.yudiz.demo.navigation.Intent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yudiz.demo.databinding.ActivityIntentThreeBinding

class IntentThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityIntentThreeBinding = ActivityIntentThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        if (bundle != null) {
            binding.intThreeTvAll.text = StringBuilder().apply {
                append(bundle.getString("email"))
                append(bundle.getString("password"))
                append(bundle.getString("address"))
                toString()
            }
        }
        binding.intThreeBtn.setOnClickListener {
            setResult(10, Intent().putExtras(bundle!!))
            finish()
        }
    }

}