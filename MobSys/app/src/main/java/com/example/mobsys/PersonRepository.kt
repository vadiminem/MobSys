package com.example.mobsys

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class PersonRepository {
    private var personDao: PersonDao? = null
    private var allPersons: LiveData<List<Person>>? = null

    constructor(application: Application) {
        val database = PersonDatabase.getInstance(application)
        personDao = database?.personDao()
        allPersons = personDao?.getAllPersons()
    }

    fun insert(person: Person) {
        InsertPersonAsyncTask(personDao).execute(person)
    }

    fun update(person: Person) {
        UpdatePersonAsyncTask(personDao).execute(person)
    }

    fun delete(person: Person) {
        DeletePersonAsyncTask(personDao).execute(person)
    }

    fun deleteAllPersons() {
        DeleteAllPersonsAsyncTask(personDao).execute()
    }

    fun getAllPersons(): LiveData<List<Person>>? {
        return allPersons
    }

    class InsertPersonAsyncTask : AsyncTask<Person, Void, Void> {

        private var personDao: PersonDao? = null

        constructor(personDao: PersonDao?) {
            this.personDao = personDao
        }

        override fun doInBackground(vararg params: Person?): Void? {
            params[0]?.let { personDao?.insert(it) }
            return null// Под вопросом
        }
    }

    class UpdatePersonAsyncTask : AsyncTask<Person, Void, Void> {

        private var personDao: PersonDao? = null

        constructor(personDao: PersonDao?) {
            this.personDao = personDao
        }

        override fun doInBackground(vararg params: Person?): Void? {
            params[0]?.let { personDao?.update(it) }
            return null// Под вопросом
        }
    }

    class DeletePersonAsyncTask : AsyncTask<Person, Void, Void> {

        private var personDao: PersonDao? = null

        constructor(personDao: PersonDao?) {
            this.personDao = personDao
        }

        override fun doInBackground(vararg params: Person?): Void? {
            params[0]?.let { personDao?.delete(it) }
            return null// Под вопросом
        }
    }

    class DeleteAllPersonsAsyncTask : AsyncTask<Void, Void, Void> {

        private var personDao: PersonDao? = null

        constructor(personDao: PersonDao?) {
            this.personDao = personDao
        }

        override fun doInBackground(vararg params: Void?): Void? {
            personDao?.deleteAllPersons()
            return null
        }
    }

}