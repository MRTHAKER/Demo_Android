package com.yudiz.demo.navigation.intent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityIntentOneBinding

class IntentOneActivity : AppCompatActivity() {
    lateinit var binding: ActivityIntentOneBinding
    val requestCode=2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntentOneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.intOneBtnNext.setOnClickListener {
            val second = Intent(this, IntentTwoActivity::class.java)
            val bundle = Bundle()
            bundle.putString(getString(R.string.email), binding.intOneEdEmail.text.toString())
            bundle.putString(getString(R.string.password), binding.intOneEdPassword.text.toString())
            second.putExtras(bundle)
            startActivityForResult(second, requestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            data?.extras?.let { binding.intOneTvResult.text = StringBuilder().apply {
                append(it.getString(getString(R.string.email)))
                append(it.getString(getString(R.string.password)))
                append(it.getString(getString(R.string.address)))
                toString()
            } }


        }
    }

}