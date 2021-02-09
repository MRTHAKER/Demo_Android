package com.yudiz.demo.layout.vector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yudiz.demo.databinding.ActivityVectorBinding

class VectorActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityVectorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityVectorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.vectorMenuAndroid.setOnClickListener(this)
        binding.vectorMenuShape.setOnClickListener(this)
        binding.vectorMenuSelector.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            binding.vectorMenuAndroid->startActivity(Intent(this,AndroidLogoActivity::class.java))
            binding.vectorMenuShape->startActivity(Intent(this,ShapeActivity::class.java))
            binding.vectorMenuSelector->startActivity(Intent(this,SelectorActivity::class.java))
        }
    }
}