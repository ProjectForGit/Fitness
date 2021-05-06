package com.example.fitness.ectomorph.trainings

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import com.example.fitness.Description
import com.example.fitness.ListAdapter.Model
import com.example.fitness.ListAdapter.MyListAdapter
import com.example.fitness.R
import com.example.fitness.ectomorph.firstFragment
import kotlinx.android.synthetic.main.activity_monday.*
import kotlin.concurrent.timer

class Monday : AppCompatActivity() {

    lateinit var listView: ListView
    var on: Boolean = false
    var time = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monday)




        listView = findViewById(R.id.listView)
        var list = mutableListOf<Model>()

        list.add(Model("Жим штанги лежа", "4х12",
            R.drawable.jimshtangi
        ))
        list.add(Model("Жим гантелей лежа на наклонной скамье", "3х10",
            R.drawable.jimgantelei
        ))
        list.add(Model("Отжимания на брусьях", "3x12",
            R.drawable.otjimaniya_na_brusyah
        ))
        list.add(Model("Жим штанги лежа узким хватом", "3х10",
            R.drawable.jim_uzkim_hvatom
        ))
        list.add(Model("Жим Арнольда", "4х10",
            R.drawable.jim_arnolda
        ))
        list.add(Model("Скручивания на скамье", "4x12",
            R.drawable.skruchivaniya_na_trenajere
        ))

        listView.adapter = MyListAdapter(this,
            R.layout.row, list)

        listView.setOnItemClickListener { parent, view, position, id ->



            if (position == 0) {

               startActivity(Intent(this, Description::class.java)
                   .apply {
                       putExtra("Name", "Жим штанги лежа")
                       putExtra("Repeat", "12")
                       putExtra("Podhod", "4")
                       putExtra("Weight", "30")
                       putExtra("Picture", R.drawable.jimshtangi)
                   })
            }
            if (position == 1) {
                startActivity(Intent(this, Description::class.java)
                    .apply {
                        putExtra("Name", "Жим гантелей лежа на наклонной скамье")
                        putExtra("Repeat", "12")
                        putExtra("Podhod", "3")
                        putExtra("Weight", "12")
                        putExtra("Picture", R.drawable.jimgantelei)
                    })
            }
            if (position == 2) {
                startActivity(Intent(this, Description::class.java)
                    .apply {
                        putExtra("Name", "Отжимания на брусьях")
                        putExtra("Repeat", "12")
                        putExtra("Podhod", "3")
                        putExtra("Weight", "собстввенный вес")
                        putExtra("Picture", R.drawable.otjimaniya_na_brusyah)
                    })
            }
            if (position == 3) {
                startActivity(Intent(this, Description::class.java)
                    .apply {
                        putExtra("Name", "Жим штанги лежа узким хватом")
                        putExtra("Repeat", "12")
                        putExtra("Podhod", "3")
                        putExtra("Weight", "10")
                        putExtra("Picture", R.drawable.jim_uzkim_hvatom)
                    })
            }
            if (position == 4) {
                startActivity(Intent(this, Description::class.java)
                    .apply {
                        putExtra("Name", "Жим Арнольда")
                        putExtra("Repeat", "10")
                        putExtra("Podhod", "4")
                        putExtra("Weight", "10")
                        putExtra("Picture", R.drawable.jim_arnolda)
                    })
            }
            if (position == 5) {
                startActivity(Intent(this, Description::class.java)
                    .apply {
                        putExtra("Name", "Скручивания на скамье")
                        putExtra("Repeat", "12")
                        putExtra("Podhod", "4")
                        putExtra("Weight", "собственный вес")
                        putExtra("Picture", R.drawable.skruchivaniya_na_trenajere)
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
            builder.setTitle("Время тренировки: $time")
                .setMessage("Выполнено упражнений: ")
                .setPositiveButton("ОК"){
                    dialog, id -> dialog.cancel()
                }.show()
            startButton.setText("Начать тренировку")
            on = false
            chronometer.setBase(SystemClock.elapsedRealtime())
        }
    }

}