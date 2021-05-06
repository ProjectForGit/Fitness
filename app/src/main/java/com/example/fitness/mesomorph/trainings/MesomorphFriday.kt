package com.example.fitness.mesomorph.trainings

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.fitness.DescriptionActivity
import com.example.fitness.ListAdapter.Exercise
import com.example.fitness.ListAdapter.ExerciseAdapter
import com.example.fitness.R
import kotlinx.android.synthetic.main.activity_monday.*

class MesomorphFriday : AppCompatActivity() {

    lateinit var listView: ListView
    var on: Boolean = false
    var time = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesomorph_friday)


        listView = findViewById(R.id.listView)
        var list = mutableListOf<Exercise>()

        list.add(
            Exercise(
                "Приседания со штангой", "4х12",
                R.drawable.prised
            )
        )
        list.add(
            Exercise(
                "Фронтальные приседания", "4х12",
                R.drawable.front_prised
            )
        )
        list.add(
            Exercise(
                "Выпады со штангой", "4х20",
                R.drawable.vipady_so_shtangoi
            )
        )
        list.add(
            Exercise(
                "Сгибания ног лежа в тренажере", "3х15",
                R.drawable.sgibaniya_nog
            )
        )
        list.add(
            Exercise(
                "Подъем на носки стоя в тренажере", "4х15",
                R.drawable.podem_na_noski
            )
        )
        list.add(
            Exercise(
                "Обратные скручивания на скамье", "3х15",
                R.drawable.obratnie_skruchivaniya
            )
        )
        list.add(
            Exercise(
                "Скручивания в тренажере", "3х15",
                R.drawable.skruchivaniya_na_trenajere
            )
        )

        listView.adapter = ExerciseAdapter(
            this,
            R.layout.row, list
        )

        listView.setOnItemClickListener { parent, view, position, id ->

            if (position == 0) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Приседания со штангой")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "4")
                            putExtra("Weight", "25")
                            putExtra("Picture", R.drawable.prised)
                        })
            }
            if (position == 1) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Фронтальные приседания")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "4")
                            putExtra("Weight", "20")
                            putExtra("Picture", R.drawable.front_prised)
                        })
            }
            if (position == 2) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Выпады со штангой")
                            putExtra("Repeat", "20")
                            putExtra("Podhod", "4")
                            putExtra("Weight", "15")
                            putExtra("Picture", R.drawable.vipady_so_shtangoi)
                        })
            }
            if (position == 3) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Сгибания ног лежа в тренажере")
                            putExtra("Repeat", "15")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "собственный вес")
                            putExtra("Picture", R.drawable.sgibaniya_nog)
                        })
            }
            if (position == 4) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Подъем на носки стоя в тренажере")
                            putExtra("Repeat", "15")
                            putExtra("Podhod", "4")
                            putExtra("Weight", "25")
                            putExtra("Picture", R.drawable.podem_na_noski)
                        })
            }
            if (position == 5) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Обратные скручивания на скамье")
                            putExtra("Repeat", "15")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "собственный вес")
                            putExtra("Picture", R.drawable.obratnie_skruchivaniya)
                        })
            }
            if (position == 6) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Скручивания в тренажере")
                            putExtra("Repeat", "15")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "собственный вес")
                            putExtra("Picture", R.drawable.skruchivaniya_na_trenajere)
                        })
            }
        }
    }

    fun startButton(view: View) {
        if (!on) {
            chronometer.visibility = view.visibility
            chronometer.start()
            Toast.makeText(this, "Тренировка начата", Toast.LENGTH_SHORT).show()
            startButton.setText("Завершить тренировку")
            on = true
        } else if (on) {
            chronometer.stop()
            time = chronometer.text.toString()
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Тренировка завершена")
                .setMessage("Время тренировки: $time")
                .setPositiveButton("ОК") { dialog, id ->
                    dialog.cancel()
                }.show()
            startButton.setText("Начать тренировку")
            on = false
            chronometer.setBase(SystemClock.elapsedRealtime())
        }
    }
}