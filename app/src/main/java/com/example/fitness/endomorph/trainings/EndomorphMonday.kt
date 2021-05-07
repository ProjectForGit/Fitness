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
import com.example.fitness.mesomorph.trainings.MesomorphMonday
import kotlinx.android.synthetic.main.activity_monday.*

class EndomorphMonday : AppCompatActivity() {

    companion object {
        lateinit var exercisesList: ArrayList<Exercise>
    }

    lateinit var listView: ListView
    var on: Boolean = false
    var time = ""
    private var exercisesValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endomorph_monday)

        listView = findViewById(R.id.listView)
        exercisesList = arrayListOf(
            Exercise("Жим штанги лежа", "4х12", R.drawable.jimshtangi),
            Exercise("Жим гантелей лежа на наклонной скамье", "3х10", R.drawable.jimgantelei),
            Exercise("Жим сидя в тренажере на грудь", "3х12", R.drawable.zhim_sidya_v_trenazhere),
            Exercise("Жим штанги стоя", "4х12", R.drawable.zhim_stoya),
            Exercise("Французский жим со штангой лежа", "3х12", R.drawable.franzyskyi_zhim),
            Exercise("Кикбек с гантелями", "3х12", R.drawable.kickback),
            Exercise("Тяга штанги к подбородку широким хватом", "4х15", R.drawable.tyaga_k_podborodku),
            Exercise("Махи гантелями в стороны", "3х15", R.drawable.mahi_gantelyami_v_storoni)
        )

        listView.adapter = ExerciseAdapter(this, R.layout.row, exercisesList)

        listView.setOnItemClickListener { parent, view, position, id ->
            val descIntent = Intent(this, DescriptionActivity::class.java)
            when (position) {
                0 -> {
                    descIntent.apply {
                                putExtra("Name", "Жим штанги лежа")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "4")
                                putExtra("Weight", "50")
                                putExtra("Picture", R.drawable.jimshtangi)
                    }
                }
                1 -> {
                    descIntent.apply {
                                putExtra("Name", "Жим гантелей лежа на наклонной скамье")
                                putExtra("Repeat", "10")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "14")
                                putExtra("Picture", R.drawable.jimgantelei)
                    }
                }
                2 -> {
                    descIntent.apply {
                                putExtra("Name", "Жим сидя в тренажере на грудь")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "25")
                                putExtra("Picture", R.drawable.zhim_sidya_v_trenazhere)
                    }
                }
                3 -> {
                    descIntent.apply {
                                putExtra("Name", "Жим штанги стоя")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "4")
                                putExtra("Weight", "20")
                                putExtra("Picture", R.drawable.zhim_stoya)
                    }
                }
                4 -> {
                    descIntent.apply {
                                putExtra("Name", "Французский жим со штангой лежа")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "20")
                                putExtra("Picture", R.drawable.franzyskyi_zhim)
                    }
                }
                5 -> {
                    descIntent.apply {
                                putExtra("Name", "Кикбек с гантелями")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "14")
                                putExtra("Picture", R.drawable.kickback)
                    }
                }
                6 -> {
                    descIntent.apply {
                                putExtra("Name", "Тяга штанги к подбородку широким хватом")
                                putExtra("Repeat", "15")
                                putExtra("Podhod", "4")
                                putExtra("Weight", "25")
                                putExtra("Picture", R.drawable.tyaga_k_podborodku)
                    }
                }
                7 -> {
                    descIntent.apply{
                                putExtra("Name", "Махи гантелями в стороны")
                                putExtra("Repeat", "15")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "10")
                                putExtra("Picture", R.drawable.mahi_gantelyami_v_storoni)
                    }
                }
            }
            descIntent.putExtra("day", "EndomorphMonday")
            descIntent.putExtra("pos", position)
            startActivity(descIntent)
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
            builder.setTitle("Время тренировки: $time")
                .setMessage("Выполнено упражнений: " + getCompletedExercisesCount() + " из $exercisesValue")
                .setPositiveButton("ОК") { dialog, id ->
                    dialog.cancel()
                }.show()
            startButton.setText("Начать тренировку")
            on = false
            chronometer.setBase(SystemClock.elapsedRealtime())
        }
    }

    private fun getCompletedExercisesCount(): Int {
        var completedExercisesCount = 0
        for (exercise in EndomorphMonday.exercisesList) {
            exercisesValue++
            if (exercise.completed) {
                completedExercisesCount++
            }
        }
        return completedExercisesCount
    }
}

