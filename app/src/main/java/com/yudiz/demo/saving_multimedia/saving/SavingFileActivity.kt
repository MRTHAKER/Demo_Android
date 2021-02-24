package com.yudiz.demo.saving_multimedia.saving

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivitySavingFileBinding
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*


class SavingFileActivity : AppCompatActivity() {
    private val imageName = "Screenshot.png"
    private lateinit var imageUri: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySavingFileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSaveShare.setOnClickListener {
            saveImage(takeScreenshot(binding.saveLinearLayout), imageName)
            val sendImage = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_STREAM, imageUri
                )
                type = "image/*"
            }
            startActivity(Intent.createChooser(sendImage, getString(R.string.share_image)))
        }
    }

    private fun takeScreenshot(v: View): Bitmap {
        val b = Bitmap.createBitmap(v.width, v.height, Bitmap.Config.ARGB_8888)
        v.draw(Canvas(b))
        return b
    }

    private fun saveImage(bitmap: Bitmap, name: String) {
        val fos: OutputStream? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val resolver = contentResolver
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            imageUri= resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)!!
            Objects.requireNonNull(imageUri)?.let { resolver.openOutputStream(it) }
        } else {
            val imagesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString()
            val image = File(imagesDir, name)
            this.imageUri = Uri.fromFile(image)
            FileOutputStream(image)
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        Objects.requireNonNull(fos)?.close()
    }
}