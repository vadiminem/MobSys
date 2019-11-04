package com.example.mobsys

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(Person::class), version = 1)
abstract class PersonDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao

    companion object {

        private var instance: PersonDatabase? = null

        fun getInstance(context: Context): PersonDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonDatabase::class.java, "mobsys_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }
            return instance
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                instance?.let { PopulateDbAsyncTask(it).execute() }
            }
        }

        open class PopulateDbAsyncTask : AsyncTask<Void, Void, Void> {
            private lateinit var personDao: PersonDao

            constructor(db: PersonDatabase) : super() {
                this.personDao = db.personDao()
            }

            override fun doInBackground(vararg params: Void?): Void? {
                personDao.insert(Person("Name1", "Surname1"))
                personDao.insert(Person("Name2", "Surname2"))
                personDao.insert(Person("Name3", "Surname3"))
                return null
            }
        }
    }
}