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

class MesomorphMonday : AppCompatActivity() {

    lateinit var listView: ListView
    var on: Boolean = false
    var time = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesomorph_monday)

        listView = findViewById(R.id.listView)
        var list = mutableListOf<Exercise>()

        list.add(
            Exercise(
                "Жим штанги на наклонной скамье", "4х10",
                R.drawable.zhim_verh
            )
        )
        list.add(
            Exercise(
                "Жим гантелей на горизонтальной скамье", "3х12",
                R.drawable.zhim_gantelei_lezha
            )
        )
        list.add(
            Exercise(
                "Отжимания на брусьях", "3х12",
                R.drawable.otjimaniya_na_brusyah
            )
        )
        list.add(
            Exercise(
                "Жим штанги лежа узким хватом", "3х10",
                R.drawable.jim_uzkim_hvatom
            )
        )
        list.add(
            Exercise(
                "Французский жим со штангой", "3х12",
                R.drawable.franzyskyi_zhim
            )
        )
        list.add(
            Exercise(
                "Жим гантелей сидя", "4х12",
                R.drawable.zhim_sidya
            )
        )
        list.add(
            Exercise(
                "Тяга штанги к подбородку широким хватом", "3х12",
                R.drawable.tyaga_k_podborodku
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
                            putExtra("Name", "Жим штанги на наклонной скамье")
                            putExtra("Repeat", "10")
                            putExtra("Podhod", "4")
                            putExtra("Weight", "20")
                            putExtra("Picture", R.drawable.zhim_verh)
                        })
            }
            if (position == 1) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Жим гантелей на горизонтальной скамье")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "50")
                            putExtra("Picture", R.drawable.zhim_gantelei_lezha)
                        })
            }
            if (position == 2) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Отжимания на брусьях")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "собственный вес")
                            putExtra("Picture", R.drawable.otjimaniya_na_brusyah)
                        })
            }
            if (position == 3) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Жим штанги лежа узким хватом")
                            putExtra("Repeat", "10")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "15")
                            putExtra("Picture", R.drawable.jim_uzkim_hvatom)
                        })
            }
            if (position == 4) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Французский жим со штангой")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "10")
                            putExtra("Picture", R.drawable.franzyskyi_zhim)
                        })
            }
            if (position == 5) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Жим гантелей сидя")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "4")
                            putExtra("Weight", "14")
                            putExtra("Picture", R.drawable.zhim_sidya)
                        })
            }
            if (position == 6) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Тяга штанги к подбородку широким хватом")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "25")
                            putExtra("Picture", R.drawable.tyaga_k_podborodku)
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