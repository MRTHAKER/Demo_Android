package com.yudiz.demo.ui.drawable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton
import com.yudiz.demo.R

class DrawableMenuActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var btnDrawable:MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_menu)
        btnDrawable=findViewById(R.id.btn_drawable_basic)
        btnDrawable.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            btnDrawable->{
                startActivity(Intent(this, DrawableBasicActivity::class.java))
            }
        }
    }
}