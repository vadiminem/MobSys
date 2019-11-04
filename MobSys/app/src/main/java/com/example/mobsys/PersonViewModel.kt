package com.example.mobsys

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

public class PersonViewModel : AndroidViewModel {
    private lateinit var repository: PersonRepository
    private var allPersons: LiveData<List<Person>>?

    public constructor(application: Application) : super(application) {
        repository = PersonRepository(application)
        allPersons = repository.getAllPersons()
    }

    fun insert(person: Person) {
        repository.insert(person)
    }

    fun update(person: Person) {
        repository.update(person)
    }

    fun delete(person: Person) {
        repository.delete(person)
    }

    fun deleteAllPersons() {
        repository.deleteAllPersons()
    }

    fun getAllPersons(): LiveData<List<Person>>? {
        return allPersons
    }
}