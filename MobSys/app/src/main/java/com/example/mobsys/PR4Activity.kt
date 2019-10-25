package com.example.mobsys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.list_item_history.*
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader

class PR4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pr4)

        val listView = findViewById<ListView>(R.id.listView)
        val historyList = intent.getSerializableExtra("history") as ArrayList<HistoryModel>
        Log.d("arrayofhistory", historyList.toString())
        /*var model = HistoryModel("test", "volot")
        historyList.add(model)*/

        val adapter = HistoryAdapter(this, historyList)
        listView.adapter = adapter
    }
}
