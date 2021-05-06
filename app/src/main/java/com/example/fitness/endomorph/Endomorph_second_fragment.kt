package com.example.fitness.endomorph

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import com.example.fitness.R


class Endomorph_second_fragment : Fragment() {


    lateinit var height: NumberPicker
    lateinit var weight: NumberPicker
    lateinit var age: NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View

        view = inflater.inflate(R.layout.fragment_second,container,false)

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
