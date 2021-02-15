package com.yudiz.demo.navigation.content_provider

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class MyHelper(context: Context?, var table: String) :
    SQLiteOpenHelper(context, name, null, version) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE EMPLOYEE(ID INTEGER, NAME TEXT)"
        )
    }

    fun Insert() {
        val db = this.writableDatabase
        val values = ContentValues()
        values.apply {
            put("ID", "1")
            put("NAME", "MAHEK")
        }
        db.insert(table, null, values)
    }

    fun Where(
    ): Cursor {
        return this.writableDatabase.query(
            table,
            arrayOf("ID", "NAME"),
            null,
            null,
            null,
            null,
            null
        )

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    companion object {
        var version = 1
        var name = "COMPANY"
    }
}