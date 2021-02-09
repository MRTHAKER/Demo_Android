package com.yudiz.demo.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityLayoutMainBinding
import com.yudiz.demo.layout.dimen.DimenActivity
import com.yudiz.demo.layout.menu.MenuActivity
import com.yudiz.demo.layout.recyclerview.RecyclerViewActivity
import com.yudiz.demo.layout.toolbar.ToolbarActivity
import com.yudiz.demo.layout.vector.VectorActivity
import com.yudiz.demo.layout.viewpager.ViewPagerActivity

class LayoutMainActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var binding:ActivityLayoutMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLayoutMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLayoutTopicsDimen.setOnClickListener(this)
        binding.btnLayoutTopicsVector.setOnClickListener(this)
        binding.btnLayoutTopicsViewpager.setOnClickListener(this)
        binding.btnLayoutTopicsRecycler.setOnClickListener(this)
        binding.btnLayoutTopicsToolbar.setOnClickListener(this)
        binding.btnLayoutTopicsMenu.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            binding.btnLayoutTopicsDimen-> startActivity(Intent(this, DimenActivity::class.java))
            binding.btnLayoutTopicsVector -> startActivity(Intent(this, VectorActivity::class.java))
            binding.btnLayoutTopicsViewpager->startActivity(Intent(this, ViewPagerActivity::class.java))
            binding.btnLayoutTopicsRecycler->startActivity(Intent(this, RecyclerViewActivity::class.java))
            binding.btnLayoutTopicsToolbar->startActivity(Intent(this, ToolbarActivity::class.java))
            binding.btnLayoutTopicsMenu->startActivity(Intent(this, MenuActivity::class.java))
        }
    }
}