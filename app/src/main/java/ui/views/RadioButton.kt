package ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import com.google.android.material.radiobutton.MaterialRadioButton
import com.yudiz.demo.R

class RadioButton : AppCompatActivity(),CompoundButton.OnCheckedChangeListener {
    lateinit var radioMale:MaterialRadioButton
    lateinit var radioFemale:MaterialRadioButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radio_button)
        radioMale=findViewById(R.id.radio_male)
        radioFemale=findViewById(R.id.radio_female)
        radioMale.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (buttonView == radioMale) {
            if (isChecked) {
                radioFemale.isChecked = false
                Toast.makeText(this, "You have selected Male", Toast.LENGTH_SHORT).show()
            }
        }
        if(buttonView==radioFemale){
            if(isChecked){
                radioMale.isChecked = false
                Toast.makeText(this, "You have selected Female", Toast.LENGTH_SHORT).show()
            }
        }
    }


}