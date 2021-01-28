package ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import android.widget.ToggleButton
import com.yudiz.demo.R

class ToggleButton : AppCompatActivity(),CompoundButton.OnCheckedChangeListener {
    lateinit var btnToggle:ToggleButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toggle_button)
        btnToggle=findViewById(R.id.btn_toggle)
        btnToggle.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if(buttonView==btnToggle){
            if(isChecked){
                Toast.makeText(this,"On", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Off", Toast.LENGTH_SHORT).show()
            }
        }
    }
}