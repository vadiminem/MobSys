package com.example.mobsys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment1.*
import kotlinx.android.synthetic.main.fragment2.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitButton.setOnClickListener {
            if (name.text.isEmpty() || surname.text.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    R.string.name_or_surname_is_empty,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    name.text.toString() + " " + surname.text.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                textOut.text = name.text.toString() + " " + surname.text.toString()
            }
        }


        submitButton2.setOnClickListener {
            if (name2.text.isEmpty() || surname2.text.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    R.string.name_or_surname_is_empty,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    name2.text.toString() + " " + surname2.text.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                textOut2.text = name2.text.toString() + " " + surname2.text.toString()
            }
        }

    }
}
