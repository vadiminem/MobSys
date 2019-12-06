package com.example.mobsys.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.net.toFile
import com.example.mobsys.services.PlayerService
import com.example.mobsys.R
import java.io.File
import java.net.URI

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


        val intent = getIntent()
        if (Intent.ACTION_SEND.equals(intent.action)) {
            val uri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
            val file = File(uri.path)
            Toast.makeText(this, file.name, Toast.LENGTH_SHORT).show()
        }

    }

    private fun openLinkInBrowser() {
        val address = Uri.parse("http://developer.alexanderklimov.ru")
        val intent = Intent(Intent.ACTION_VIEW, address)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
            Toast.makeText(this, "Link was open", Toast.LENGTH_SHORT).show()
        } else {
            Log.d("INTENT", "Unable to process intent")
        }
    }

}
