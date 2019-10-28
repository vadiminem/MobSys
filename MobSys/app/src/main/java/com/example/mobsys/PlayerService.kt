package com.example.mobsys

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Environment
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.io.File

class PlayerService : Service() {

    private val filename = "service_log"
    private var mPlayer = MediaPlayer()
    private var handler = Handler()
    private lateinit var writeCurrentPositionThread: WriteCurrentPositionThread


    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Service created", Toast.LENGTH_SHORT).show()
        mPlayer = MediaPlayer.create(this, R.raw.sample)
        mPlayer.setLooping(false)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show()

        val path = filesDir
        //val file = File(path, filename)
        val file = File(path, filename)
        file.writeText("")
        //Log.d("FILENAME", testFile.absolutePath)
        //if (!file.exists()) file.mkdir()

        writeCurrentPositionThread = WriteCurrentPositionThread(mPlayer, handler, file)
        handler.postDelayed(writeCurrentPositionThread, 50)
        mPlayer.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show()
        mPlayer.stop()
        handler.removeCallbacks(writeCurrentPositionThread)
    }

    class WriteCurrentPositionThread : Runnable {
        private var handler = Handler()
        private var mediaPlayer = MediaPlayer()
        private val file: File

        override fun run() {
            file.appendText(mediaPlayer.currentPosition.toString() + "\n")
            handler.postDelayed(this, 50)
        }

        constructor(mediaPlayer: MediaPlayer, handler: Handler, file: File) {
            this.mediaPlayer = mediaPlayer
            this.handler = handler
            this.file = file
        }
    }

}
