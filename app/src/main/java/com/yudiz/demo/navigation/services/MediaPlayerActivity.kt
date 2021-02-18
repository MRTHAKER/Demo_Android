package com.yudiz.demo.navigation.services

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityMediaPlayerBinding
import com.yudiz.demo.navigation.services.models.SongModel

class MediaPlayerActivity : AppCompatActivity(), ItemClickListener {
    val storageCode = 1
    private lateinit var mediaCursor: Cursor
    lateinit var serviceIntent: Intent
    lateinit var songList: MutableList<SongModel>
    lateinit var adapter: RecyclerMediaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMediaPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        serviceIntent = Intent(this, MediaPlayerService::class.java)
        if (checkSelf(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            getAudioFiles()
            adapter = RecyclerMediaAdapter(songList).apply { listener = this@MediaPlayerActivity }
            binding.rvMediaView.adapter = adapter
            binding.rvMediaView.layoutManager = LinearLayoutManager(this)
        } else {
            requestPermissions(
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ), storageCode
            )
        }

    }

    private fun checkSelf(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            storageCode -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getAudioFiles()
                    Toast.makeText(
                        this,
                        getString(R.string.read_storage_granted),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    MaterialAlertDialogBuilder(this).apply {
                        setTitle("Attention!")
                        setIcon(R.drawable.ic_warning)
                        setMessage(getString(R.string.storage_message_settings))
                        setNegativeButton(getString(R.string.ok), null)
                        show()
                    }
                }
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun getAudioFiles() {
        contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null)
            ?.also { mediaCursor = it }
        if (mediaCursor.moveToFirst()) {
            songList = mutableListOf()
            do {
                val id = mediaCursor.getLong(mediaCursor.getColumnIndex(MediaStore.Audio.Media._ID))
                val name =
                    mediaCursor.getString(mediaCursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                val path =
                    ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
                songList.add(SongModel(name, path, getDrawable(R.drawable.ic_play)!!, getDrawable(R.drawable.ic_pause)!!,getDrawable(R.drawable.ic_stop)!!))
            } while (mediaCursor.moveToNext())
            mediaCursor.close()
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onClick(view: View, position: Int,song:Uri) {
        when(view.id){
            R.id.iv_media_play->{
                serviceIntent.action="play"
                serviceIntent.putExtra("uri",song)
                startService(serviceIntent)
            }
            R.id.iv_media_pause->{
                serviceIntent.action="pause"
                startService(serviceIntent)
            }
            R.id.iv_media_stop->{
                serviceIntent.action="stop"
                stopService(serviceIntent)
            }
        }
    }
}