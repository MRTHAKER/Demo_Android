package ui.layouts

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.yudiz.demo.R
import java.util.regex.Pattern

class CustomInflater : LinearLayout {
    var typed:TypedArray?=null
    lateinit var label:String
    lateinit var hint:String
    lateinit var imgRight:Drawable
    lateinit var imgWrong:Drawable
    var regex = "^(.+)@(.+)$"
    constructor(context: Context?) : super(context){
        init(context,null,0)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init(context,attrs,0)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init(context,attrs,defStyleAttr)
    }

    private fun init(context: Context?, attrs: AttributeSet?, style: Int) {
        val view=LayoutInflater.from(context).inflate(
            R.layout.custom_layout,this,true)
        attrs?.let {
            typed= context?.theme?.obtainStyledAttributes(attrs,R.styleable.CustomInflater,style,0) }
        val edt=view.findViewById<TextView>(R.id.custom_text)
        val txtView=view.findViewById<EditText>(R.id.custom_edt_email)
        val image=view.findViewById<ImageView>(R.id.img_right_wrong)
        try {
            label = typed?.getString(R.styleable.CustomInflater_label) ?: ""
            hint=typed?.getString(R.styleable.CustomInflater_android_hint)?:""
            imgRight= typed?.getDrawable(R.styleable.CustomInflater_rightImage)!!
            imgWrong=typed?.getDrawable(R.styleable.CustomInflater_wrongImage)!!
        }
        finally {
            typed?.recycle()
        }
        edt.text=label
        txtView.hint=hint
        txtView.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val p=Pattern.compile(regex)
                val match=p.matcher(txtView.text)
                when {
                    txtView.text.isEmpty() -> {
                        image.setImageDrawable(null)
                    }
                    match.matches() -> {
                        image.setImageDrawable(imgRight)
                    }
                    else -> {
                        image.setImageDrawable(imgWrong)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })


    }
}