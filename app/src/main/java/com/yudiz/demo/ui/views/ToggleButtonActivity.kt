package com.yudiz.demo.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import com.yudiz.demo.databinding.ActivityToggleButtonBinding


class ToggleButtonActivity : AppCompatActivity(),CompoundButton.OnCheckedChangeListener {
    lateinit var binding: ActivityToggleButtonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityToggleButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnToggle.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if(buttonView==binding.btnToggle){
            if(isChecked){
                Toast.makeText(this,"On", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Off", Toast.LENGTH_SHORT).show()
            }
        }
    }


}