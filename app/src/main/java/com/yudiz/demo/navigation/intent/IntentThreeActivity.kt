package com.yudiz.demo.navigation.intent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityIntentThreeBinding

class IntentThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityIntentThreeBinding = ActivityIntentThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        bundle?.let {
            binding.intThreeTvAll.text = StringBuilder().run {
                append(bundle.getString(getString(R.string.email)))
                append(bundle.getString(getString(R.string.password)))
                append(bundle.getString(getString(R.string.password)))
                toString()
            }
        }

        binding.intThreeBtn.setOnClickListener {
            setResult(Activity.RESULT_OK, bundle?.let { x -> Intent().putExtras(x) })
            finish()
        }
    }

}