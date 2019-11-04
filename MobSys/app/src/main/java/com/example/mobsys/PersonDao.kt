package com.example.mobsys

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonDao {

    @Insert
    fun insert(person: Person)

    @Update
    fun update(person: Person)

    @Delete
    fun delete(person: Person)

    @Query("DELETE FROM person_table")
    fun deleteAllPersons()

    @Query("SELECT * FROM person_table")
    fun getAllPersons(): LiveData<List<Person>>
}