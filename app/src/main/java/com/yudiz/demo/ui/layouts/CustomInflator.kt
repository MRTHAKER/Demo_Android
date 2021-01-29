package com.yudiz.demo.ui.layouts

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.yudiz.demo.R
import com.yudiz.demo.databinding.CustomLayoutBinding
import java.util.regex.Pattern

class CustomInflater : LinearLayout {
    var typed:TypedArray?=null
    lateinit var label:String
    lateinit var hint:String
    lateinit var imgRight:Drawable
    lateinit var imgWrong:Drawable
    lateinit var binding: CustomLayoutBinding
    var regex = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})"
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
        binding= CustomLayoutBinding.inflate(LayoutInflater.from(context),this,true)
        attrs?.let {
            typed= context?.obtainStyledAttributes(attrs,R.styleable.CustomInflater,style,0) }
        try {
            label = typed?.getString(R.styleable.CustomInflater_label) ?: ""
            hint=typed?.getString(R.styleable.CustomInflater_android_hint)?:""
            imgRight= typed?.getDrawable(R.styleable.CustomInflater_rightImage)!!
            imgWrong=typed?.getDrawable(R.styleable.CustomInflater_wrongImage)!!
        }
        finally {
            typed?.recycle()
        }
        binding.customLayoutTextView.text=label
        binding.customLayoutEditText.hint=hint
        binding.customLayoutEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val p=Pattern.compile(regex)
                val match=p.matcher(binding.customLayoutEditText.text)
                when {
                    binding.customLayoutEditText.text.isEmpty() -> {
                        binding.customLayoutImageView.setImageDrawable(null)
                    }
                    match.matches() -> {
                        binding.customLayoutImageView.setImageDrawable(imgRight)
                    }
                    else -> {
                        binding.customLayoutImageView.setImageDrawable(imgWrong)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })


    }
}