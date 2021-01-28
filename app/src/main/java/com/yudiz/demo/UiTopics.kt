package com.yudiz.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton
import ui.layouts.Layouts
import ui.views.Views

class UiTopics : AppCompatActivity(),View.OnClickListener {
    lateinit var btnViews:MaterialButton
    lateinit var btnLayouts:MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_topics)
        btnViews=findViewById(R.id.btn_ui_topics_views)
        btnLayouts=findViewById(R.id.btn_ui_topics_layouts)
        btnLayouts.setOnClickListener(this)
        btnViews.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v==btnViews){
            startActivity(Intent(this,Views::class.java))
        }
        if(v==btnLayouts){
            startActivity(Intent(this,Layouts::class.java))
        }
    }
}