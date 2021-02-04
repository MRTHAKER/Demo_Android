package com.yudiz.demo.ui.toolbar

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityToolbarBinding

class ToolbarActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables")
    lateinit var binding: ActivityToolbarBinding

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToolbarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainToolbar.setNavigationOnClickListener { finish() }
        binding.toolbarFab.setOnClickListener {
            Snackbar.make(binding.toolbarLayout, "Hii thanks for Click!", Snackbar.LENGTH_INDEFINITE).setAction("Close") { Toast.makeText(this, "You have clicked on close ", Toast.LENGTH_SHORT).show() }.setActionTextColor(getColor(R.color.teal_700)).show()
        }
    }
}