package ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import com.yudiz.demo.R

class Switch : AppCompatActivity(),CompoundButton.OnCheckedChangeListener {
    lateinit var switch:Switch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch)
        switch=findViewById(R.id.btn_switch)
        switch.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if(buttonView==switch){
            if(isChecked){
                Toast.makeText(this,"On",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Off",Toast.LENGTH_SHORT).show()
            }
        }
    }
}