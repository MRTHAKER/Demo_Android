package com.yudiz.demo.ui.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.yudiz.demo.R

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_demo, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(
            this, when (item.itemId) {
                R.id.menu_call -> "Call"
                R.id.menu_video_call -> "Video call"
                R.id.menu_view_contact -> "View contact"
                R.id.menu_media -> "Media"
                R.id.menu_more -> "More"
                R.id.menu_search -> "Search"
                R.id.subitem_block -> "Block"
                R.id.subitem_clear -> "Clear"
                else -> " "
            }, Toast.LENGTH_SHORT
        ).show()

        return true
    }
}