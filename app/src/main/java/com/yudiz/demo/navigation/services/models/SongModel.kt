package com.yudiz.demo.navigation.services.models

import android.graphics.drawable.Drawable
import android.net.Uri

data class SongModel(val songName:String, val SongUri:Uri, var playIcon: Drawable,var pauseIcon:Drawable,var stopIcon:Drawable) {
}