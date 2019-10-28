package com.example.mobsys.data

import android.provider.BaseColumns

class HistoryContract {

    class History : BaseColumns {
        val TABLE_NAME = "history"

         val _ID = BaseColumns._ID
         val COLUMN_NAME = "name"
         val COLUMN_SURNAME = "surname"
    }
}