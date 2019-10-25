package com.example.mobsys

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class PR31Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pr3_1)
    }

    internal fun addToHistory(historyItem: HistoryModel) {
        val result = Intent()
        result.putExtra("resultItem", historyItem)
        Log.d("HISTORYITEM", historyItem.getName())
        setResult(1, result)
        finish()
    }

}
