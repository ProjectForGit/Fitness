package com.example.fitness.ectomorph.trainings

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

class Friday : AppCompatActivity() {

    lateinit var listView: ListView
    var on: Boolean = false
    var time = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friday)


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
                "Жим ногами", "3х10",
                R.drawable.zhim_nogami
            )
        )
        list.add(
            Exercise(
                "Румынская тяга с гантелями", "4х12",
                R.drawable.rumynskaya_yaga_ganteli
            )
        )
        list.add(
            Exercise(
                "Сгибания ног лежа в тренажере", "3х12",
                R.drawable.sgibaniya_nog
            )
        )
        list.add(
            Exercise(
                "Подъем на носки стоя в тренажере", "4х15",
                R.drawable.podem_na_noski
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
                            putExtra("Weight", "10")
                            putExtra("Picture", R.drawable.prised)
                        })
            }
            if (position == 1) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Жим ногами")
                            putExtra("Repeat", "10")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "20")
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
                            putExtra("Weight", "8")
                            putExtra("Picture", R.drawable.rumynskaya_yaga_ganteli)
                        })
            }
            if (position == 3) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Сгибания ног лежа в тренажере")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "20")
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