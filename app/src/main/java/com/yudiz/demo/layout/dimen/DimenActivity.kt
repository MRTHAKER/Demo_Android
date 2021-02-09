package com.yudiz.demo.layout.dimen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yudiz.demo.databinding.ActivityDimenBinding

class DimenActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var binding:ActivityDimenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDimenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.dimenMenuLogin.setOnClickListener(this)
        binding.dimenMenuMultiScreen.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            binding.dimenMenuLogin-> startActivity(Intent(this,LoginActivity::class.java))
            binding.dimenMenuMultiScreen->startActivity(Intent(this,MultiScreenActivity::class.java))
        }
    }
}