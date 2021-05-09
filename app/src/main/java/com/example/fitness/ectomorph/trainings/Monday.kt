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
import com.example.fitness.endomorph.trainings.EndomorphMonday
import kotlinx.android.synthetic.main.activity_monday.*

class Monday : AppCompatActivity() {

    companion object {
        lateinit var exercisesList: ArrayList<Exercise>
        var buttonState = false
    }

    lateinit var listView: ListView
    var on: Boolean = false
    var time = ""
    private var exercisesValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monday)

        listView = findViewById(R.id.listView)
        exercisesList = arrayListOf(
            Exercise("Жим штанги лежа", "4х12", R.drawable.jimshtangi),
            Exercise("Жим гантелей лежа на наклонной скамье", "3х10", R.drawable.jimgantelei),
            Exercise("Отжимания на брусьях", "3x12", R.drawable.otjimaniya_na_brusyah),
            Exercise("Жим штанги лежа узким хватом", "3х10", R.drawable.jim_uzkim_hvatom),
            Exercise("Жим Арнольда", "4х10", R.drawable.jim_arnolda),
            Exercise("Скручивания на скамье", "4x12", R.drawable.skruchivaniya_na_trenajere)
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
                        putExtra("Weight", "30")
                        putExtra("Picture", R.drawable.jimshtangi)
                    }
                }
                1 -> {
                    descIntent.apply {
                        putExtra("Name", "Жим гантелей лежа на наклонной скамье")
                        putExtra("Repeat", "12")
                        putExtra("Podhod", "3")
                        putExtra("Weight", "12")
                        putExtra("Picture", R.drawable.jimgantelei)
                    }
                }
                2 -> {
                    descIntent.apply {
                        putExtra("Name", "Отжимания на брусьях")
                        putExtra("Repeat", "12")
                        putExtra("Podhod", "3")
                        putExtra("Weight", "собстввенный вес")
                        putExtra("Picture", R.drawable.otjimaniya_na_brusyah)
                    }
                }
                3 -> {
                    descIntent.apply {
                        putExtra("Name", "Жим штанги лежа узким хватом")
                        putExtra("Repeat", "12")
                        putExtra("Podhod", "3")
                        putExtra("Weight", "10")
                        putExtra("Picture", R.drawable.jim_uzkim_hvatom)
                    }
                }
                4 -> {
                    descIntent.apply {
                        putExtra("Name", "Жим Арнольда")
                        putExtra("Repeat", "10")
                        putExtra("Podhod", "4")
                        putExtra("Weight", "10")
                        putExtra("Picture", R.drawable.jim_arnolda)
                    }
                }
                5 -> {
                    descIntent.apply {
                        putExtra("Name", "Скручивания на скамье")
                        putExtra("Repeat", "12")
                        putExtra("Podhod", "4")
                        putExtra("Weight", "собственный вес")
                        putExtra("Picture", R.drawable.skruchivaniya_na_trenajere)
                    }
                }
            }
            descIntent.putExtra("day", "Monday")
            descIntent.putExtra("pos", position)
            startActivity(descIntent)
        }
    }

    fun startButton(view: View) {
        if (!on) {
            chronometer.base = SystemClock.elapsedRealtime()
            chronometer.visibility = view.visibility
            chronometer.start()
            Toast.makeText(this, "Тренировка начата", Toast.LENGTH_SHORT).show()
            startButton.setText("Завершить тренировку")
            on = true

            buttonState = true

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

            buttonState = false
        }
    }

    private fun getCompletedExercisesCount(): Int {
        exercisesValue = 0
        var completedExercisesCount = 0
        for (exercise in exercisesList) {
            exercisesValue++
            if (exercise.completed) {
                completedExercisesCount++
                exercise.completed = false
            }
        }
        return completedExercisesCount
    }
}