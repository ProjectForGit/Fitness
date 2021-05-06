package com.example.fitness.ectomorph

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import com.example.fitness.R
import kotlinx.android.synthetic.main.fragment_second.*

class secondFragment : Fragment() {

    lateinit var height: NumberPicker
    lateinit var weight: NumberPicker
    lateinit var age: NumberPicker

    lateinit var kc : TextView
    var kcalValue = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        var view: View
        view = inflater.inflate(R.layout.fragment_second,container,false)

        //getData
        if (activity!!.intent.hasExtra("Kcal"))
        {
            val extras = activity!!.intent.extras
            kcalValue = extras!!.getString("Kcal").toString()
        }


        kc = view.findViewById(R.id.kcal)
        kc.text = kcalValue

        height = view.findViewById(R.id.heightPicker)
        height.minValue = 140
        height.maxValue = 220
        height.value = 160
        height.wrapSelectorWheel = false


        weight = view.findViewById(R.id.weightPicker)
        weight.minValue = 40
        weight.maxValue = 150
        weight.value = 70
        weight.wrapSelectorWheel = false


        age = view.findViewById(R.id.agePicker)
        age.minValue = 14
        age.maxValue = 70
        age.value = 20
        age.wrapSelectorWheel = false

        return view
    }



}