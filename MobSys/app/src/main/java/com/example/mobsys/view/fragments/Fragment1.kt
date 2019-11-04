package com.example.mobsys.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mobsys.R
import com.example.mobsys.model.HistoryModel
import com.example.mobsys.MainActivity
import com.example.mobsys.view.PR31Activity
import kotlinx.android.synthetic.main.fragment1.*
import kotlinx.android.synthetic.main.fragment1.view.*

class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment1, container, false)

        view.submitButton.setOnClickListener {
            if (name.text.isEmpty() || surname.text.isEmpty()) {
                Toast.makeText(
                    context,
                    R.string.name_or_surname_is_empty,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val textOutValue = name.text.toString() + " " + surname.text.toString()
                Toast.makeText(
                    context,
                    textOutValue,
                    Toast.LENGTH_SHORT
                ).show()
                textOut.text = textOutValue
                addToHistory(name.text.toString(), surname.text.toString())
            }
        }
        return view
    }

    private fun addToHistory(name: String, surname: String) {
        /*if (activity is MainActivity)
        (activity as MainActivity).addToHistory(
            HistoryModel(
                name,
                surname
            )
        )
        else if (activity is PR31Activity)
            (activity as PR31Activity).addToHistory(
                HistoryModel(
                    name,
                    surname
                )
            )*/
    }

}
