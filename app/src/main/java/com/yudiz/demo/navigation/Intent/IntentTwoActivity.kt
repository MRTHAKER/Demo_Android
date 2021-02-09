package com.yudiz.demo.navigation.Intent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yudiz.demo.databinding.ActivityIntentTwoBinding

class IntentTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityIntentTwoBinding = ActivityIntentTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        binding.intTwoBtnNext.setOnClickListener {
            val third = Intent(this, IntentThreeActivity::class.java)
            bundle?.putString("address", binding.intTwoEtAddress.toString())
            third.putExtras(bundle!!)
            startActivityForResult(third, 2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 10) {
            setResult(10, data)
            finish()
        }
    }
}