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
import com.example.fitness.ectomorph.trainings.Monday
import kotlinx.android.synthetic.main.activity_monday.*

class EndomorphFriday : AppCompatActivity() {

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
        setContentView(R.layout.activity_endomorph_friday)

        listView = findViewById(R.id.listView)
        exercisesList = arrayListOf(
            Exercise("Приседания со штангой", "4х12", R.drawable.prised),
            Exercise("Жим ногами", "3х12", R.drawable.zhim_nogami),
            Exercise("Румынская тяга с гантелями", "4х12", R.drawable.rumynskaya_yaga_ganteli),
            Exercise("Выпады с гантелями", "3х12", R.drawable.vipady_s_gantelyami),
            Exercise("Подъем на носки стоя в тренажере", "4х15", R.drawable.podem_na_noski),
            Exercise("Скручивания на скамье", "3х15", R.drawable.skruchivaniya_na_trenajere),
            Exercise("Подъемы ног в висе", "3х12", R.drawable.podem_nog_v_vise_na_turnike)
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
                                putExtra("Weight", "20")
                                putExtra("Picture", R.drawable.prised)
                    }
                }
                1 -> {
                    descIntent.apply {
                                putExtra("Name", "Жим ногами")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "50")
                                putExtra("Picture", R.drawable.zhim_nogami)
                    }
                }
                2 -> {
                    descIntent.apply {
                                putExtra("Name", "Румынская тяга с гантелями")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "4")
                                putExtra("Weight", "16")
                                putExtra("Picture", R.drawable.rumynskaya_yaga_ganteli)
                    }
                }
                3 -> {
                    descIntent.apply {
                                putExtra("Name", "Выпады с гантелями")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "16")
                                putExtra("Picture", R.drawable.vipady_s_gantelyami)
                    }
                }
                4 -> {
                    descIntent.apply {
                                putExtra("Name", "Подъем на носки стоя в тренажере")
                                putExtra("Repeat", "15")
                                putExtra("Podhod", "4")
                                putExtra("Weight", "30")
                                putExtra("Picture", R.drawable.podem_na_noski)
                    }
                }
                5 -> {
                    descIntent.apply {
                                putExtra("Name", "Скручивания на скамье")
                                putExtra("Repeat", "15")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "собственный вес")
                                putExtra("Picture", R.drawable.skruchivaniya_na_trenajere)
                    }
                }
                6 -> {
                    descIntent.apply {
                                putExtra("Name", "Подъемы ног в висе")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "собственный вес")
                                putExtra("Picture", R.drawable.podem_nog_v_vise_na_turnike)
                    }
                }
            }
            descIntent.putExtra("day", "EndomorphFriday")
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