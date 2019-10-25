package com.example.mobsys

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment1.*
import kotlinx.android.synthetic.main.fragment1.view.*

class Fragment2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment1, container, false)

        view.submitButton.setOnClickListener {
            if (surname.text.isEmpty() || name.text.isEmpty()) {
                Toast.makeText(
                    context,
                    R.string.name_or_surname_is_empty,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context,
                    surname.text.toString() + " " + name.text.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                textOut.text = String.format("%s %s", surname.text, name.text)

                addToHistory(name.text.toString(), surname.text.toString())
            }
        }
        return view
    }

    private fun addToHistory(name: String, surname: String) {

        (activity as MainActivity).addToHistory(HistoryModel(name, surname))
    }
}