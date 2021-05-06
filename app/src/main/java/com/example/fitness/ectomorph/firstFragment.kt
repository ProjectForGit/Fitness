package com.example.fitness.ectomorph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitness.R


@Suppress("UNREACHABLE_CODE")
class firstFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)


        /* val array = arrayOf("Понедельник","Среда","Пятница")

        val adapter = ArrayAdapter(
            context!!,
            android.R.layout.simple_list_item_1,
            array
        )

        val listView =
            view?.findViewById<View>(R.id.daysList) as ListView
        listView.adapter = adapter


        return view */

    }

}