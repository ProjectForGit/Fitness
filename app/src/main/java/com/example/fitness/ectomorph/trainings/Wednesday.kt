package com.example.fitness.ectomorph.trainings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.fitness.Description
import com.example.fitness.ListAdapter.Model
import com.example.fitness.ListAdapter.MyListAdapter
import com.example.fitness.R
import kotlinx.android.synthetic.main.activity_monday.*
import kotlinx.android.synthetic.main.activity_wednesday.*
import kotlinx.android.synthetic.main.activity_wednesday.startButton
import kotlinx.android.synthetic.main.activity_monday.chronometer as chronometer1

class Wednesday : AppCompatActivity() {

    lateinit var listView: ListView
    var on: Boolean = false
    var time = ""

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wednesday)

        listView = findViewById(R.id.listView)
        var list = mutableListOf<Model>()

        list.add(
            Model("Становая тяга", "4х12",
            R.drawable.stanovaya
        )
        )
        list.add(
            Model("Подтягивания широким хватом", "4x12",
            R.drawable.podtyagivaniya_shirokim_hvatom
        )
        )
        list.add(
            Model("Тяга гантели в наклоне", "3х10",
            R.drawable.tyaga_ganteli
        )
        )
        list.add(
            Model("Тяга верхнего блока узким обратным хватом", "3х10",
            R.drawable.tyaga_blocka
        )
        )
        list.add(
            Model("Подъемы штанги на бицепс", "3х12",
            R.drawable.shtanga_na_biceps
        )
        )
        list.add(
            Model("Подъемы ног в висе", "3x12",
            R.drawable.podem_nog_v_vise_na_turnike
        )
        )

        listView.adapter = MyListAdapter(this,
            R.layout.row, list)

        listView.setOnItemClickListener { parent, view, position, id ->

            if (position == 0) {
                startActivity(
                    Intent(this, Description::class.java)
                    .apply {
                        putExtra("Name", "Становая тяга")
                        putExtra("Repeat", "12")
                        putExtra("Podhod", "4")
                        putExtra("Weight", "20")
                        putExtra("Picture", R.drawable.stanovaya)
                    })
            }
            if (position == 1) {
                startActivity(
                    Intent(this, Description::class.java)
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
                    Intent(this, Description::class.java)
                        .apply {
                            putExtra("Name", "Тяга гантели в наклоне")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "18")
                            putExtra("Picture", R.drawable.tyaga_ganteli)
                        })
            }
            if (position == 3) {
                startActivity(
                    Intent(this, Description::class.java)
                        .apply {
                            putExtra("Name", "Тяга верхнего блока узким обратным хватом")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "25")
                            putExtra("Picture", R.drawable.tyaga_blocka)
                        })
            }
            if (position == 4) {
                startActivity(
                    Intent(this, Description::class.java)
                        .apply {
                            putExtra("Name", "Подъемы штанги на бицепс")
                            putExtra("Repeat", "12")
                            putExtra("Podhod", "3")
                            putExtra("Weight", "15")
                            putExtra("Picture", R.drawable.shtanga_na_biceps)
                        })
            }
            if (position == 5) {
                startActivity(
                    Intent(this, Description::class.java)
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