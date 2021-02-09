package com.yudiz.demo.layout.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityRecyclerviewViewBinding
import com.yudiz.demo.layout.recyclerview.models.Animal

class RecyclerViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerviewViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerviewViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val list = listOf<Animal>(Animal("Tiger", ContextCompat.getDrawable(this, R.drawable.ic_android)!!), Animal("Lions", ContextCompat.getDrawable(this, R.drawable.ic_done_black_48)!!))
        binding.RecyclerView.adapter = RecyclerAdapter(list)
        binding.RecyclerView.layoutManager = LinearLayoutManager(this)
    }
}