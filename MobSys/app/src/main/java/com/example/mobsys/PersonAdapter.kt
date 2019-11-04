package com.example.mobsys

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

open class PersonAdapter : ListAdapter<Person, PersonAdapter.PersonHolder> {

    companion object {
        private lateinit var listener: OnItemClickListener
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem.getId() == newItem.getId()
            }

            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem.getName().equals(newItem.getName()) && oldItem.getSurname().equals(
                    newItem.getSurname()
                )
            }
        }
    }
    constructor() : super(DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.person_item, parent, false)
        return PersonHolder(itemView)
    }

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        val currentPerson = getItem(position)
        holder.textViewName.setText(currentPerson.getName())
        holder.textViewSurname.setText(currentPerson.getSurname())
    }

    fun getPersonAt(position: Int): Person {
        return getItem(position)
    }

    inner class PersonHolder : RecyclerView.ViewHolder {
        var textViewName: TextView
        var textViewSurname: TextView

        constructor(itemView: View) : super(itemView) {
            this.textViewName = itemView.findViewById(R.id.text_view_person_name)
            this.textViewSurname = itemView.findViewById(R.id.text_view_person_surname)

            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position))
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(person: Person)
    }

    fun setOnItemClickListener(_listener: OnItemClickListener) {
        listener = _listener
    }
}