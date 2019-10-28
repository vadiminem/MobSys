package com.example.mobsys

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        findViewById<Button>(R.id.open_link_in_browser_button)?.setOnClickListener {
            openLinkInBrowser()
        }
    }

    private fun openLinkInBrowser() {
        val address = Uri.parse("http://developer.alexanderklimov.ru")
        val intent = Intent(Intent.ACTION_VIEW, address)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("INTENT", "Unable to process intent")
        }
    }
}
