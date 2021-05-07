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

class EndomorphWednesday : AppCompatActivity() {

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
        setContentView(R.layout.activity_endomorph_wednesday)

        listView = findViewById(R.id.listView)
        exercisesList = arrayListOf(
            Exercise("Подтягивания широким хватом", "4х15", R.drawable.podtyagivaniya_shirokim_hvatom),
            Exercise("Тяга штанги в наклоне", "4х10", R.drawable.tyaga_k_poyasu),
            Exercise("Тяга вертикального блока узким обратным хватом", "3х10", R.drawable.tyaga_blocka),
            Exercise("Тяга гантели в наклоне", "3x10", R.drawable.tyaga_ganteli),
            Exercise("Гиперэкстензия", "4х15", R.drawable.giperekstenziya),
            Exercise("Подъемы штанги на бицепс стоя", "3х12", R.drawable.shtanga_na_biceps),
            Exercise("Сгибания рук со гантелями на скамье Скотта", "3х10", R.drawable.bitseps_skott),
            Exercise("Отведения в тренажере на заднюю дельту", "4х15", R.drawable.otvedenie_na_zadnyu_delty)
        )

        listView.adapter = ExerciseAdapter(this, R.layout.row, exercisesList)

        listView.setOnItemClickListener { parent, view, position, id ->
            val descIntent = Intent(this, DescriptionActivity::class.java)
            when (position) {
                0 -> {
                    descIntent.apply {
                                putExtra("Name", "Подтягивания широким хватом")
                                putExtra("Repeat", "15")
                                putExtra("Podhod", "4")
                                putExtra("Weight", "собственный вес")
                                putExtra("Picture", R.drawable.podtyagivaniya_shirokim_hvatom)
                    }
                }
                1 -> {
                    descIntent.apply {
                                putExtra("Name", "Тяга штанги в наклоне")
                                putExtra("Repeat", "10")
                                putExtra("Podhod", "4")
                                putExtra("Weight", "15")
                                putExtra("Picture", R.drawable.tyaga_k_poyasu)
                    }
                }
                2 -> {
                    descIntent.apply {
                                putExtra("Name", "Тяга вертикального блока узким обратным хватом")
                                putExtra("Repeat", "10")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "30")
                                putExtra("Picture", R.drawable.tyaga_blocka)
                    }
                }
                3 -> {
                    descIntent.apply {
                                putExtra("Name", "Тяга гантели в наклоне")
                                putExtra("Repeat", "10")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "30")
                                putExtra("Picture", R.drawable.tyaga_ganteli)
                    }
                }
                4 -> {
                    descIntent.apply {
                                putExtra("Name", "Гиперэкстензия")
                                putExtra("Repeat", "15")
                                putExtra("Podhod", "4")
                                putExtra("Weight", "15")
                                putExtra("Picture", R.drawable.giperekstenziya)
                    }
                }
                5 -> {
                    descIntent.apply {
                                putExtra("Name", "Подъемы штанги на бицепс стоя")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "25")
                                putExtra("Picture", R.drawable.shtanga_na_biceps)
                    }
                }
                6 -> {
                    descIntent.apply {
                                putExtra("Name", "Сгибания рук со гантелями на скамье Скотта")
                                putExtra("Repeat", "10")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "12")
                                putExtra("Picture", R.drawable.bitseps_skott)
                    }
                }
                7 -> {
                    descIntent.apply {
                                putExtra("Name", "Отведения в тренажере на заднюю дельту")
                                putExtra("Repeat", "15")
                                putExtra("Podhod", "4")
                                putExtra("Weight", "25")
                                putExtra("Picture", R.drawable.otvedenie_na_zadnyu_delty)
                    }
                }
            }
            descIntent.putExtra("day", "EndomorphWednesday")
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