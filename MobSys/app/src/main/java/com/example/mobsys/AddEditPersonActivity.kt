package com.example.mobsys

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast

class AddEditPersonActivity : AppCompatActivity() {
    var EXTRA_ID = "com.example.mobsys.EXTRA_ID"
    var EXTRA_NAME = "com.example.mobsys.EXTRA_NAME"
    var EXTRA_SURNAME = "com.example.mobsys.EXTRA_SURNAME"

    private lateinit var editTextName: EditText
    private lateinit var editTextSurname: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person)

        editTextName = findViewById(R.id.edit_text_person_name)
        editTextSurname = findViewById(R.id.edit_text_person_surname)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        val intent = intent

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit person"
            editTextName.setText(intent.getStringExtra(EXTRA_NAME))
            editTextSurname.setText(intent.getStringExtra(EXTRA_SURNAME))
        } else {
            title = "Add person"
        }
    }

    private fun savePerson() {
        val name = editTextName.text.toString()
        val surname = editTextSurname.text.toString()

        if (name.trim().isEmpty() || surname.trim().isEmpty()) {
            Toast.makeText(this, "Please insert name and surname", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent()
        data.putExtra(EXTRA_NAME, name)
        data.putExtra(EXTRA_SURNAME, surname)

        val id = intent.getIntExtra(EXTRA_ID, -1)
        if (id != -1) {
            data.putExtra(EXTRA_ID, id)
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_person_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_person -> {
                savePerson()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
