package com.yudiz.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton
import com.yudiz.demo.ui.drawable.DrawableMenuActivity
import com.yudiz.demo.ui.layouts.LayoutsActivity
import com.yudiz.demo.ui.views.ViewsActivity

class UiTopicsActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var btnViews:MaterialButton
    lateinit var btnLayouts:MaterialButton
    lateinit var btnDrawable:MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_topics)
        btnViews=findViewById(R.id.btn_ui_topics_views)
        btnLayouts=findViewById(R.id.btn_ui_topics_layouts)
        btnDrawable=findViewById(R.id.btn_ui_topics_drawable)
        btnLayouts.setOnClickListener(this)
        btnViews.setOnClickListener(this)
        btnDrawable.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            btnViews ->{
                startActivity(Intent(this, ViewsActivity::class.java))
            }
            btnLayouts->{
                startActivity(Intent(this, LayoutsActivity::class.java))
            }
            btnDrawable->{
                startActivity(Intent(this, DrawableMenuActivity::class.java))
            }
        }
    }
}