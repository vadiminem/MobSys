package com.example.mobsys

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

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

    internal fun addToHistory(historyItem: HistoryModel) {
        history.add(historyItem)
    }

}
