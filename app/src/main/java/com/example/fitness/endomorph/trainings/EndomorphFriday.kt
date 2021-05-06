package com.example.fitness.endomorph.trainings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.fitness.DescriptionActivity
import com.example.fitness.ListAdapter.Exercise
import com.example.fitness.ListAdapter.ExerciseAdapter
import com.example.fitness.R
import kotlinx.android.synthetic.main.activity_monday.*

class EndomorphFriday : AppCompatActivity() {

    lateinit var listView: ListView
    var on: Boolean = false
    var time = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endomorph_friday)



        listView = findViewById(R.id.listView)
        var list = mutableListOf<Exercise>()

        list.add(
            Exercise("Приседания со штангой", "4х12",
                R.drawable.prised
            )
        )
        list.add(
            Exercise("Жим ногами", "3х12",
                R.drawable.zhim_nogami
            )
        )
        list.add(
            Exercise("Румынская тяга с гантелями", "4х12",
                R.drawable.rumynskaya_yaga_ganteli
            )
        )
        list.add(
            Exercise("Выпады с гантелями", "3х12",
                R.drawable.vipady_s_gantelyami
            )
        )
        list.add(
            Exercise("Подъем на носки стоя в тренажере", "4х15",
                R.drawable.podem_na_noski
            )
        )
        list.add(
            Exercise("Скручивания на скамье", "3х15",
                R.drawable.skruchivaniya_na_trenajere
            )
        )
        list.add(
            Exercise("Подъемы ног в висе", "3х12",
                R.drawable.podem_nog_v_vise_na_turnike
            )
        )

        listView.adapter = ExerciseAdapter(this,
            R.layout.row, list)

        listView.setOnItemClickListener { parent, view, position, id ->

            if (position == 0) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Приседания со штангой")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "4")
                            putExtra("Weight", "20")
                            putExtra("Picture", R.drawable.prised)
                        })
            }
            if (position == 1) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Жим ногами")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "50")
                            putExtra("Picture", R.drawable.zhim_nogami)
                        })
            }
            if (position == 2) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Румынская тяга с гантелями")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "4")
                            putExtra("Weight", "16")
                            putExtra("Picture", R.drawable.rumynskaya_yaga_ganteli)
                        })
            }
            if (position == 3) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Выпады с гантелями")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "16")
                            putExtra("Picture", R.drawable.vipady_s_gantelyami)
                        })
            }
            if (position == 4) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Подъем на носки стоя в тренажере")
                            putExtra("Repeat", "15")
                            putExtra("Podhod", "4")
                            putExtra("Weight", "30")
                            putExtra("Picture", R.drawable.podem_na_noski)
                        })
            }
            if (position == 5) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Скручивания на скамье")
                            putExtra("Repeat", "15")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "собственный вес")
                            putExtra("Picture", R.drawable.skruchivaniya_na_trenajere)
                        })
            }
            if (position == 6) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Подъемы ног в висе")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "собственный вес")
                            putExtra("Picture", R.drawable.podem_nog_v_vise_na_turnike)
                        })
            }
        }
    }

    fun startButton(view: View) {
        if (!on)
        {
            chronometer.visibility = view.visibility
            chronometer.start()
            Toast.makeText(this, "Тренировка начата", Toast.LENGTH_SHORT).show()
            startButton.setText("Завершить тренировку")
            on = true
        }
        else if (on)
        {
            chronometer.stop()
            time = chronometer.text.toString()
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Тренировка завершена")
                .setMessage("Время тренировки: $time")
                .setPositiveButton("ОК"){
                        dialog, id -> dialog.cancel()
                }.show()
            startButton.setText("Начать тренировку")
            on = false
            chronometer.setBase(SystemClock.elapsedRealtime())
        }
    }
}