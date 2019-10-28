package com.example.mobsys.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mobsys.data.HistoryContract.History

class HistoryDbHelper : SQLiteOpenHelper {

    constructor(context: Context) : super(context, "mobsysdb", null, 1)

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val SQL_CREATE_HISTORY_TABLE = "CREATE TABLE " +
                History().TABLE_NAME + " (" +
                History()._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                History().COLUMN_NAME + " TEXT NOT NULL, " +
                History().COLUMN_SURNAME + " TEXT NOT NULL);"

        db?.execSQL(SQL_CREATE_HISTORY_TABLE)
    }
}