package com.yudiz.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var btn_ui:MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_ui=findViewById(R.id.btn_ui)
        btn_ui.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v==btn_ui){
            startActivity(Intent(this,UiTopicsActivity::class.java))
        }
    }
}