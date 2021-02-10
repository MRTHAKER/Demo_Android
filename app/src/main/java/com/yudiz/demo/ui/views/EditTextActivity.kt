package com.yudiz.demo.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.yudiz.demo.R

class EditTextActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var textViewCounter: MaterialTextView
    lateinit var editTextPassword: TextInputEditText
    lateinit var textViewPassword: MaterialTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)
        editText = findViewById(R.id.edit_text_counter)
        textViewCounter = findViewById(R.id.text_view_counter)
        editTextPassword = findViewById(R.id.password_max)
        textViewPassword = findViewById(R.id.txt_view_password)
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textViewCounter.text = editText.length().toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textViewPassword.text = editTextPassword.length().toString()
            }

            override fun afterTextChanged(s: Editable?) {
                if (editTextPassword.length() > 8) {
                    editTextPassword.error = getString(R.string.hint_passwd_max_eight)
                }

            }
        })

    }


}