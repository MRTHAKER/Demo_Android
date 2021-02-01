package com.yudiz.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton
import com.yudiz.demo.databinding.ActivityUiTopicsBinding
import com.yudiz.demo.ui.dimen.DimenActivity
import com.yudiz.demo.ui.drawable.DrawableMenuActivity
import com.yudiz.demo.ui.layouts.LayoutsActivity
import com.yudiz.demo.ui.views.ViewsActivity

class UiTopicsActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var binding: ActivityUiTopicsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUiTopicsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnUiTopicsLayouts.setOnClickListener(this)
        binding.btnUiTopicsViews.setOnClickListener(this)
        binding.btnUiTopicsDrawable.setOnClickListener(this)
        binding.btnUiTopisDimen.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            binding.btnUiTopicsViews -> startActivity(Intent(this, ViewsActivity::class.java))
            binding.btnUiTopicsLayouts-> startActivity(Intent(this, LayoutsActivity::class.java))
            binding.btnUiTopicsDrawable-> startActivity(Intent(this, DrawableMenuActivity::class.java))
            binding.btnUiTopisDimen-> startActivity(Intent(this,DimenActivity::class.java))
        }
    }
}