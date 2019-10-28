package com.example.mobsys

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mobsys.data.HistoryContract
import com.example.mobsys.data.HistoryContract.History
import com.example.mobsys.data.HistoryDbHelper

class MainActivity : AppCompatActivity() {

    var history = arrayListOf<HistoryModel>()
    private val RESULT_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.openActivityButton)?.setOnClickListener {
            val intent = Intent(this, PR31Activity::class.java)
            startActivityForResult(intent, RESULT_CODE)
        }

        findViewById<Button>(R.id.openPR4)?.setOnClickListener {
            readFromDb()
            val intent = Intent(this, PR4Activity::class.java)
            intent.putExtra("history", history)
            startActivity(intent)
        }

        findViewById<Button>(R.id.player)?.setOnClickListener {
            val intent = Intent(this, Player::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_CODE && data != null) {
            if (resultCode == 1) {
                val historyItem =
                    data.getSerializableExtra("resultItem") as HistoryModel
                addToHistory(historyItem)
            }
        }
    }

    private fun readFromDb() {
        val db = HistoryDbHelper(this).readableDatabase
        val cursor = db.query(
            History().TABLE_NAME,
            arrayOf(History().COLUMN_NAME, History().COLUMN_SURNAME),
            null,
            null,
            null,
            null,
            null
        )

        val nameColumnIndex = cursor.getColumnIndex(History().COLUMN_NAME)
        val surnameColumnIndex = cursor.getColumnIndex(History().COLUMN_SURNAME)

        while (cursor.moveToNext()) {

            history.add(
                HistoryModel(
                    cursor.getString(nameColumnIndex),
                    cursor.getString(surnameColumnIndex)
                )
            )
        }
    }

    internal fun addToHistory(historyItem: HistoryModel) {
        val db = HistoryDbHelper(this).writableDatabase
        val values = ContentValues()
        values.put(History().COLUMN_NAME, historyItem.getName())
        values.put(History().COLUMN_SURNAME, historyItem.getSurname())

        db.insert(History().TABLE_NAME, null, values)
    }

}
