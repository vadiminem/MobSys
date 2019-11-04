package com.example.mobsys

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    val ADD_PERSON_REQUEST = 1
    val EDIT_PERSON_REQUEST = 2

    private lateinit var personViewModel: PersonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAddPerson = findViewById<FloatingActionButton>(R.id.button_add_person)
        buttonAddPerson.setOnClickListener {
            val intent = Intent(this, AddEditPersonActivity::class.java)
            startActivityForResult(intent, ADD_PERSON_REQUEST)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val personAdapter = PersonAdapter()
        recyclerView.adapter = personAdapter

        personViewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
        personViewModel.getAllPersons()?.observe(this,
            Observer<List<Person>> { persons -> personAdapter.submitList(persons) })

        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                personViewModel.delete(personAdapter.getPersonAt(viewHolder.adapterPosition))
                Toast.makeText(applicationContext, "Person deleted", Toast.LENGTH_SHORT).show()
            }

        }
        ).attachToRecyclerView(recyclerView)

        personAdapter.setOnItemClickListener(object : PersonAdapter.OnItemClickListener {
            override fun onItemClick(person: Person) {
                val intent = Intent(applicationContext, AddEditPersonActivity::class.java)
                intent.putExtra(AddEditPersonActivity().EXTRA_ID, person.getId())
                intent.putExtra(AddEditPersonActivity().EXTRA_NAME, person.getName())
                intent.putExtra(AddEditPersonActivity().EXTRA_SURNAME, person.getSurname())
                startActivityForResult(intent, EDIT_PERSON_REQUEST)
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_PERSON_REQUEST && resultCode == RESULT_OK) {
            val name = data?.getStringExtra(AddEditPersonActivity().EXTRA_NAME)
            val surname = data?.getStringExtra(AddEditPersonActivity().EXTRA_SURNAME)

            val person = Person(name, surname)
            personViewModel.insert(person)

            Toast.makeText(this, "Person saved", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_PERSON_REQUEST && resultCode == RESULT_OK) {
            val id = data?.getIntExtra(AddEditPersonActivity().EXTRA_ID, -1)

            if (id == -1) {
                Toast.makeText(this, "Person can't be updated", Toast.LENGTH_SHORT).show()
                return
            }

            val name = data?.getStringExtra(AddEditPersonActivity().EXTRA_NAME)
            val surname = data?.getStringExtra(AddEditPersonActivity().EXTRA_SURNAME)

            val person = Person(name, surname)
            id?.let { person.setId(it) }
            personViewModel.update(person)

        } else {
            Toast.makeText(this, "Person not saved", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all_persons -> {
                personViewModel.deleteAllPersons()
                Toast.makeText(this, "All persons deleted", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
