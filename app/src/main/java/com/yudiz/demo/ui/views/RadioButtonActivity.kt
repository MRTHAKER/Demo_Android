package com.yudiz.demo.ui.views

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.android.material.radiobutton.MaterialRadioButton
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityRadioButtonBinding

class RadioButtonActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener{
    lateinit var binder: ActivityRadioButtonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder= ActivityRadioButtonBinding.inflate(layoutInflater)
        setContentView(binder.root)
        binder.genderRadioGroup.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        if(group==binder.genderRadioGroup){
            if(checkedId==binder.radioMale.id){
                Toast.makeText(this,"Male",Toast.LENGTH_SHORT).show()
            }

                if(checkedId==binder.radioFemale.id){
                    Toast.makeText(this,"Female",Toast.LENGTH_SHORT).show()
                }
        }
    }
}