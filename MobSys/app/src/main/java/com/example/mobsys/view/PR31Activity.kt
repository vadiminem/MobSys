package com.example.mobsys.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobsys.R
import com.example.mobsys.model.HistoryModel

class PR31Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pr3_1)
    }

    internal fun addToHistory(historyItem: HistoryModel) {
        val result = Intent()
        result.putExtra("resultItem", historyItem)
        setResult(1, result)
        finish()
    }

}
