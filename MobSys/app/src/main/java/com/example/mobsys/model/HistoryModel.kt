package com.example.mobsys.model

import java.io.Serializable


class HistoryModel : Serializable{

    var id: Long = 0
    private var name: String = ""
    private var surname: String = ""


    constructor(){}
    constructor(name: String, surname: String){
        this.name = name
        this.surname = surname
    }
    fun getName(): String {
        return name
    }

    fun getSurname(): String {
        return surname
    }
}