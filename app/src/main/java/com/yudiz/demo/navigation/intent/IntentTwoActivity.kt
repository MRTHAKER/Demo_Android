package com.yudiz.demo.navigation.intent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityIntentTwoBinding

class IntentTwoActivity : AppCompatActivity() {
    val requestCode=3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityIntentTwoBinding = ActivityIntentTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        binding.intTwoBtnNext.setOnClickListener {
            val third = Intent(this, IntentThreeActivity::class.java)
            bundle?.putString(getString(R.string.address), binding.intTwoEtAddress.toString())
            third.putExtras(bundle!!)
            startActivityForResult(third, requestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }
}