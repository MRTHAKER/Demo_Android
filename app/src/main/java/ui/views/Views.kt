package ui.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton
import com.yudiz.demo.R

class Views : AppCompatActivity(),View.OnClickListener {
    lateinit var btnTextview: MaterialButton
    lateinit var btnEditText: MaterialButton
    lateinit var btnCheckBox: MaterialButton
    lateinit var btnRadioButton: MaterialButton
    lateinit var btnToggleButton:MaterialButton
    lateinit var btnSwitch: MaterialButton
    lateinit var btnImageView:MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_views)
        btnTextview=findViewById(R.id.btn_view_textview)
        btnEditText=findViewById(R.id.btn_view_edittext)
        btnCheckBox=findViewById(R.id.btn_view_checkbox)
        btnRadioButton=findViewById(R.id.btn_view_radiobutton)
        btnToggleButton=findViewById(R.id.btn_view_toggle)
        btnSwitch=findViewById(R.id.btn_view_switch)
        btnImageView=findViewById(R.id.btn_view_imageview)
        btnTextview.setOnClickListener(this)
        btnEditText.setOnClickListener(this)
        btnCheckBox.setOnClickListener(this)
        btnRadioButton.setOnClickListener(this)
        btnToggleButton.setOnClickListener(this)
        btnSwitch.setOnClickListener(this)
        btnImageView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            btnTextview->{
            startActivity(Intent(this,Textview::class.java))
            }
            btnEditText->{
                startActivity(Intent(this,EditText::class.java))
            }
            btnCheckBox->{
                startActivity(Intent(this,CheckBox::class.java))
            }
            btnRadioButton->{
                startActivity(Intent(this,RadioButton::class.java))
            }
            btnToggleButton->{
                startActivity(Intent(this,ToggleButton::class.java))
            }
            btnSwitch->{
                startActivity(Intent(this,Switch::class.java))
            }
            btnImageView->{
                startActivity(Intent(this,ImageView::class.java))
        }

        }
    }
}