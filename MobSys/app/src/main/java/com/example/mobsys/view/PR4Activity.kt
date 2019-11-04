package com.example.mobsys.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.example.mobsys.R
import com.example.mobsys.model.HistoryModel

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
