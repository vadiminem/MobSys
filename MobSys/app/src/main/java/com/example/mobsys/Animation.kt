package com.example.mobsys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

class Animation : AppCompatActivity() {

    private lateinit var imageView : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)


        imageView = findViewById(R.id.button)
        var animation = AnimationUtils.loadAnimation(this, R.anim.enlarge)

        imageView.startAnimation(animation)

    }


}
