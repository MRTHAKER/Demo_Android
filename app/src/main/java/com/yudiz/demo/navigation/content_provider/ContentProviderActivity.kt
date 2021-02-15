package com.yudiz.demo.navigation.content_provider

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityContentProviderBinding
import java.lang.StringBuilder

class ContentProviderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityContentProviderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var authority = "com.yudiz.demo.navigation.content_provider"
        var contentUri: Uri = Uri.parse("content://${authority}")
        binding.btnGetContent.setOnClickListener {
            val cursor = contentResolver.query(contentUri, null, null, null, null, null)
            contentResolver.insert(contentUri,null)
            cursor?.moveToFirst()
            val sb = StringBuilder("lol")
            while (cursor?.moveToNext() == true) {
                sb.append(cursor.getString(cursor.getColumnIndex("ID")))
                sb.append(cursor.getString(cursor.getColumnIndex("NAME")))
            }
            binding.cpTextView.text = sb.toString()
        }
    }

    }