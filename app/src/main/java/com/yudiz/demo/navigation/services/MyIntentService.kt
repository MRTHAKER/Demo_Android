package com.yudiz.demo.navigation.services

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.ResultReceiver
import com.yudiz.demo.R
import java.io.FileOutputStream
import java.io.OutputStream

class MyIntentService : IntentService("MyIntentService") {
    var resultReceiver: ResultReceiver? = null
    override fun onHandleIntent(intent: Intent?) {
        resultReceiver = intent?.getParcelableExtra(getString(R.string.handler))
        val image: Bitmap? = intent?.getParcelableExtra("image")
        resultReceiver?.send(1, Bundle().apply {
            putParcelable("image",
                image?.let { resizeImage(it) })
        })
    }

    private fun resizeImage(image: Bitmap): Bitmap {
        val width = image.width
        val height = image.height
        val scaleWidth = width / 10
        val scaleHeight = height / 10
        if (image.byteCount <= 1000000)
            return image
        return Bitmap.createScaledBitmap(image, scaleWidth, scaleHeight, false)
    }
}