package com.yudiz.demo.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivitySwitchBinding

class SwitchActivity : AppCompatActivity(),CompoundButton.OnCheckedChangeListener {
    lateinit var binding:ActivitySwitchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySwitchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSwitch.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if(buttonView==binding.btnSwitch){
            if(isChecked){
                Toast.makeText(this,"On",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Off",Toast.LENGTH_SHORT).show()
            }
        }
    }
}