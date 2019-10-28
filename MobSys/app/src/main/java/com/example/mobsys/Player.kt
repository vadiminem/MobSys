package com.example.mobsys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Player : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        findViewById<Button>(R.id.play_button)?.setOnClickListener {
            startService(Intent(this, PlayerService::class.java))
        }

        findViewById<Button>(R.id.stop_button)?.setOnClickListener {
            stopService(Intent(this, PlayerService::class.java))
        }
    }
}
