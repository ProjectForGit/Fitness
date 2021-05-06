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

class MesomorphWednesday : AppCompatActivity() {

    lateinit var listView: ListView
    var on: Boolean = false
    var time = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesomorph_wednesday)



        listView = findViewById(R.id.listView)
        var list = mutableListOf<Exercise>()

        list.add(
            Exercise(
                "Становая тяга", "4х12",
                R.drawable.stanovaya
            )
        )
        list.add(
            Exercise(
                "Подтягивания широким хватом", "4х12",
                R.drawable.podtyagivaniya_shirokim_hvatom
            )
        )
        list.add(
            Exercise(
                "Тяга штанги в наклоне", "3х10",
                R.drawable.tyaga_k_poyasu
            )
        )
        list.add(
            Exercise(
                "Тяга вертикального блока узким обратным хватом", "3х12",
                R.drawable.tyaga_blocka
            )
        )
        list.add(
            Exercise(
                "Горизонтальная тяга", "3х12",
                R.drawable.gorizontalnaya_tyaga
            )
        )
        list.add(
            Exercise(
                "Подъемы гантелей на бицепс сидя на наклонной скамье", "4х10",
                R.drawable.bitseps_ganteli
            )
        )
        list.add(
            Exercise(
                "Махи гантелями в наклоне", "4х15",
                R.drawable.mahi_v_naklone
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
                            putExtra("Name", "Становая тяга")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "25")
                            putExtra("Picture", R.drawable.stanovaya)
                        })
            }
            if (position == 1) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Подтягивания широким хватом")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "4")
                            putExtra("Weight", "собственный вес")
                            putExtra("Picture", R.drawable.podtyagivaniya_shirokim_hvatom)
                        })
            }
            if (position == 2) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Тяга штанги в наклоне")
                            putExtra("Repeat", "10")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "15")
                            putExtra("Picture", R.drawable.tyaga_k_poyasu)
                        })
            }
            if (position == 3) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Тяга вертикального блока узким обратным хватом")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "25")
                            putExtra("Picture", R.drawable.tyaga_blocka)
                        })
            }
            if (position == 4) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Горизонтальная тяга")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "25")
                            putExtra("Picture", R.drawable.gorizontalnaya_tyaga)
                        })
            }
            if (position == 6) {
                startActivity(
                    Intent(this, DescriptionActivity::class.java)
                        .apply {
                            putExtra("Name", "Махи гантелями в наклоне")
                            putExtra("Repeat", "15")
                            putExtra("Podhod", "4")
                            putExtra("Weight", "10")
                            putExtra("Picture", R.drawable.mahi_v_naklone)
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