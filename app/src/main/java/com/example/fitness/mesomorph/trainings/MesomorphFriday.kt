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

    companion object {
        lateinit var exercisesList: ArrayList<Exercise>
    }

    lateinit var listView: ListView
    var on: Boolean = false
    var time = ""
    private var exercisesValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesomorph_friday)


        listView = findViewById(R.id.listView)
        exercisesList = arrayListOf(
            Exercise("Приседания со штангой", "4х12", R.drawable.prised),
            Exercise("Фронтальные приседания", "4х12", R.drawable.front_prised),
            Exercise("Выпады со штангой", "4х20", R.drawable.vipady_so_shtangoi),
            Exercise("Сгибания ног лежа в тренажере", "3х15", R.drawable.sgibaniya_nog),
            Exercise("Подъем на носки стоя в тренажере", "4х15", R.drawable.podem_na_noski),
            Exercise("Обратные скручивания на скамье", "3х15", R.drawable.obratnie_skruchivaniya),
            Exercise("Скручивания в тренажере", "3х15", R.drawable.skruchivaniya_na_trenajere)
        )

        listView.adapter = ExerciseAdapter(this, R.layout.row, exercisesList)

        listView.setOnItemClickListener { parent, view, position, id ->
            val descIntent = Intent(this, DescriptionActivity::class.java)
            when (position) {
                0 -> {
                    descIntent.apply {
                                putExtra("Name", "Приседания со штангой")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "4")
                                putExtra("Weight", "25")
                                putExtra("Picture", R.drawable.prised)
                    }
                }
                1 -> {
                    descIntent.apply {
                                putExtra("Name", "Фронтальные приседания")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "4")
                                putExtra("Weight", "20")
                                putExtra("Picture", R.drawable.front_prised)
                    }
                }
                2 -> {
                    descIntent.apply {
                                putExtra("Name", "Выпады со штангой")
                                putExtra("Repeat", "20")
                                putExtra("Podhod", "4")
                                putExtra("Weight", "15")
                                putExtra("Picture", R.drawable.vipady_so_shtangoi)
                    }
                }
                3 -> {
                    descIntent.apply {
                                putExtra("Name", "Сгибания ног лежа в тренажере")
                                putExtra("Repeat", "15")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "собственный вес")
                                putExtra("Picture", R.drawable.sgibaniya_nog)
                    }
                }
                4 -> {
                    descIntent.apply {
                                putExtra("Name", "Подъем на носки стоя в тренажере")
                                putExtra("Repeat", "15")
                                putExtra("Podhod", "4")
                                putExtra("Weight", "25")
                                putExtra("Picture", R.drawable.podem_na_noski)
                    }
                }
                5 -> {
                    descIntent.apply {
                                putExtra("Name", "Обратные скручивания на скамье")
                                putExtra("Repeat", "15")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "собственный вес")
                                putExtra("Picture", R.drawable.obratnie_skruchivaniya)
                    }
                }
                6 -> {
                    descIntent.apply {
                                putExtra("Name", "Скручивания в тренажере")
                                putExtra("Repeat", "15")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "собственный вес")
                                putExtra("Picture", R.drawable.skruchivaniya_na_trenajere)
                    }
                }
            }
            descIntent.putExtra("day", "MesomorphFriday")
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
        for (exercise in MesomorphFriday.exercisesList) {
            exercisesValue++
            if (exercise.completed) {
                completedExercisesCount++
            }
        }
        return completedExercisesCount
    }
}