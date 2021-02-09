package com.yudiz.demo.navigation.Intent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yudiz.demo.databinding.ActivityIntentOneBinding

class IntentOneActivity : AppCompatActivity() {
    lateinit var binding: ActivityIntentOneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntentOneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.intOneBtnNext.setOnClickListener {
            val second = Intent(this, IntentTwoActivity::class.java)
            val bundle = Bundle()
            bundle.putString("email", binding.intOneEdEmail.text.toString())
            bundle.putString("password", binding.intOneEdPassword.text.toString())
            second.putExtras(bundle)
            startActivityForResult(second, 2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 10) {
            val bundle = data?.extras
            if (bundle != null) {
                binding.intOneTvResult.text = StringBuilder().apply {
                    append(bundle.getString("email"))
                    append(bundle.getString("password"))
                    append(bundle.getString("address"))
                    toString()
                }

            }
        }
    }

}