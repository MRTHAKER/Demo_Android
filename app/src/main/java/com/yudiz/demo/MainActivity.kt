package com.yudiz.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton
import com.yudiz.demo.databinding.ActivityMainBinding
import com.yudiz.demo.layout.LayoutMainActivity
import com.yudiz.demo.navigation.NavigationActivity

class MainActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnUi.setOnClickListener(this)
        binding.btnNavigation.setOnClickListener(this)
        binding.btnLayout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            binding.btnUi->startActivity(Intent(this,UiTopicsActivity::class.java))
            binding.btnNavigation->startActivity(Intent(this,NavigationActivity::class.java))
            binding.btnLayout->startActivity(Intent(this,LayoutMainActivity::class.java))
        }
    }
}