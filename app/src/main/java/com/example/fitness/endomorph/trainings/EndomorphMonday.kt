package com.example.fitness.endomorph.trainings

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

class EndomorphMonday : AppCompatActivity() {

    lateinit var listView: ListView
    var on: Boolean = false
    var time = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endomorph_monday)


        listView = findViewById(R.id.listView)
        var list = mutableListOf<Exercise>()

        list.add(
            Exercise(
                "Жим штанги лежа", "4х12",
                R.drawable.jimshtangi
            )
        )
        list.add(
            Exercise(
                "Жим гантелей лежа на наклонной скамье", "3х10",
                R.drawable.jimgantelei
            )
        )
        list.add(
            Exercise(
                "Жим сидя в тренажере на грудь", "3х12",
                R.drawable.zhim_sidya_v_trenazhere
            )
        )
        list.add(
            Exercise(
                "Жим штанги стоя", "4х12",
                R.drawable.zhim_stoya
            )
        )
        list.add(
            Exercise(
                "Французский жим со штангой лежа", "3х12",
                R.drawable.franzyskyi_zhim
            )
        )
        list.add(
            Exercise(
                "Кикбек с гантелями", "3х12",
                R.drawable.kickback
            )
        )
        list.add(
            Exercise(
                "Тяга штанги к подбородку широким хватом", "4х15",
                R.drawable.tyaga_k_podborodku
            )
        )
        list.add(
            Exercise(
                "Махи гантелями в стороны", "3х15",
                R.drawable.mahi_gantelyami_v_storoni
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
                            putExtra("Name", "Жим штанги лежа")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "4")
                            putExtra("Weight", "50")
                            putExtra("Picture", R.drawable.jimshtangi)
                        })
            }
            if (position == 1) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Жим гантелей лежа на наклонной скамье")
                            putExtra("Repeat", "10")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "14")
                            putExtra("Picture", R.drawable.jimgantelei)
                        })
            }
            if (position == 2) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Жим сидя в тренажере на грудь")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "25")
                            putExtra("Picture", R.drawable.zhim_sidya_v_trenazhere)
                        })
            }
            if (position == 3) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Жим штанги стоя")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "4")
                            putExtra("Weight", "20")
                            putExtra("Picture", R.drawable.zhim_stoya)
                        })
            }
            if (position == 4) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Французский жим со штангой лежа")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "20")
                            putExtra("Picture", R.drawable.franzyskyi_zhim)
                        })
            }
            if (position == 5) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Кикбек с гантелями")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "14")
                            putExtra("Picture", R.drawable.kickback)
                        })
            }
            if (position == 6) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Тяга штанги к подбородку широким хватом")
                            putExtra("Repeat", "15")
                            putExtra("Podhod", "4")
                            putExtra("Weight", "25")
                            putExtra("Picture", R.drawable.tyaga_k_podborodku)
                        })
            }
            if (position == 7) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Махи гантелями в стороны")
                            putExtra("Repeat", "15")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "10")
                            putExtra("Picture", R.drawable.mahi_gantelyami_v_storoni)
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