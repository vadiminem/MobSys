package com.example.mobsys

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView


class HistoryAdapter(
    private val context: Context,
    private val dataSource: ArrayList<HistoryModel>
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.list_item_history, parent, false)

        val nameTextView = rowView.findViewById(R.id.name) as TextView
        val surnameTextView = rowView.findViewById(R.id.surname) as TextView

        val itemHistory = getItem(position) as HistoryModel

        nameTextView.text = itemHistory.getName()
        surnameTextView.text = itemHistory.getSurname()

        return rowView

    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}