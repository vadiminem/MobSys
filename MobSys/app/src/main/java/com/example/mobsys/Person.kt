package com.example.mobsys

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "person_table")
class Person {

    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0

    private var name: String?

    private var surname: String?

    constructor(name: String?, surname: String?) {
        this.name = name
        this.surname = surname
    }


    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun getSurname(): String? {
        return surname
    }
}