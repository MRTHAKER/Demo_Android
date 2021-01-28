package ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.yudiz.demo.R
import android.view.View
import com.google.android.material.textview.MaterialTextView

class Textview : AppCompatActivity() , View.OnClickListener  {
    lateinit var btnCounter : MaterialButton
    var count=0
    private lateinit var txtViewCounter: MaterialTextView
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_textview)
        btnCounter = findViewById<MaterialButton>(R.id.btn_click_count)
        txtViewCounter=findViewById<MaterialTextView>(R.id.txt_view_counter)
        btnCounter.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btnCounter -> {
                count+=1
                txtViewCounter.text = "${getString(R.string.count)} $count"
            }
        }
    }
}