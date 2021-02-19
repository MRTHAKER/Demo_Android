package com.yudiz.demo.navigation.services

import android.Manifest
import android.annotation.SuppressLint
import android.content.*
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yudiz.demo.R
import com.yudiz.demo.databinding.ActivityMediaPlayerBinding
import com.yudiz.demo.navigation.services.models.SongModel

class MediaPlayerActivity : AppCompatActivity(), ItemClickListener{
    val storageCode = 1
    private lateinit var mediaCursor: Cursor
    lateinit var serviceIntent: Intent
    lateinit var songList: MutableList<SongModel>
    lateinit var adapter: RecyclerMediaAdapter
    lateinit var holder: RecyclerMediaAdapter.RecyclerViewMediaHolder

    override fun onStart() {
        super.onStart()

        registerReceiver(receiver,IntentFilter().apply {
            addAction(getString(R.string.playMedia))
            addAction(getString(R.string.pauseMedia))
            addAction(getString(R.string.stopMedia))
        })
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }

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
                songList.add(SongModel(name, path))
            } while (mediaCursor.moveToNext())
            mediaCursor.close()
        }
    }

    override fun onClick(view: View, holder: RecyclerMediaAdapter.RecyclerViewMediaHolder, song: Uri, songName: String) {
        when (view.id) {
            R.id.iv_media_play -> {
                view.visibility = View.GONE
                serviceIntent.action = "play"
                serviceIntent.putExtra("song", songName)
                serviceIntent.putExtra("uri", song)
                this.holder=holder
                startService(serviceIntent)
            }
            R.id.iv_media_pause -> {
                view.visibility = View.GONE
                serviceIntent.action = "pause"
                this.holder=holder
                startService(serviceIntent)
            }
            R.id.iv_media_stop -> {
                serviceIntent.action = "stop"
                this.holder=holder
                stopService(serviceIntent)
            }
        }
    }
    val receiver= object :BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action){
                context?.getString(R.string.playMedia)->{
                    holder.play.callOnClick()
                }
                context?.getString(R.string.pauseMedia)->{
                    holder.pause.callOnClick()
                }
                context?.getString(R.string.stopMedia)->{
                    holder.stop.callOnClick()
                }
            }
        }

    }
}