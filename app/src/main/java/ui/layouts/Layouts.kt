package ui.layouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton
import com.yudiz.demo.R

class Layouts : AppCompatActivity(),View.OnClickListener {
    lateinit var btnLinear:MaterialButton
    lateinit var btnRealtive:MaterialButton
    lateinit var btnConstraint:MaterialButton
    lateinit var btnFrame:MaterialButton
    lateinit var btnCustom:MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layouts)
        btnLinear=findViewById(R.id.btn_layouts_linear)
        btnRealtive=findViewById(R.id.btn_layouts_relative)
        btnConstraint=findViewById(R.id.btn_layouts_constraint)
        btnFrame=findViewById(R.id.btn_layouts_frame)
        btnCustom=findViewById(R.id.btn_layouts_custom)
        btnFrame.setOnClickListener(this)
        btnLinear.setOnClickListener(this)
        btnRealtive.setOnClickListener(this)
        btnConstraint.setOnClickListener(this)
        btnCustom.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v){
            btnLinear->{
                startActivity(Intent(this,LinearLayout::class.java))
            }
            btnRealtive->{
                startActivity(Intent(this,RelativeLayout::class.java))
            }
            btnConstraint->{
                startActivity(Intent(this,ConstraintLayout::class.java))
            }
            btnFrame->{
                startActivity(Intent(this,FrameLayout::class.java))
            }
            btnCustom->{
                startActivity(Intent(this,CustomLayout::class.java))
            }
        }
    }
}