package com.yudiz.demo.navigation.content_provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class CustomProvider: ContentProvider() {
    lateinit var  uriMatcher:UriMatcher
    lateinit var db:MyHelper
    var matchCode=2
    var authority="com.yudiz.demo.navigation.content_provider.CustomProvider"

    override fun onCreate(): Boolean {
        uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(authority, "EMPLOYEE/#", matchCode)
        }
        db=MyHelper(context,"EMPLOYEE")
        db.Insert()
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor {
        return db.Where()
    }

    override fun getType(uri: Uri): String? {
        return ""
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return Uri.EMPTY
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}