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
import kotlinx.android.synthetic.main.activity_wednesday.*
import kotlinx.android.synthetic.main.activity_wednesday.chronometer
import kotlinx.android.synthetic.main.activity_wednesday.startButton

class Wednesday : AppCompatActivity() {

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
        setContentView(R.layout.activity_wednesday)

        listView = findViewById(R.id.listView)
       exercisesList = arrayListOf(
            Exercise("Становая тяга", "4х12", R.drawable.stanovaya),
            Exercise("Подтягивания широким хватом", "4x12", R.drawable.podtyagivaniya_shirokim_hvatom),
            Exercise("Тяга гантели в наклоне", "3х10", R.drawable.tyaga_ganteli),
            Exercise("Тяга верхнего блока узким обратным хватом", "3х10", R.drawable.tyaga_blocka),
            Exercise("Подъемы штанги на бицепс", "3х12", R.drawable.shtanga_na_biceps),
            Exercise("Подъемы ног в висе", "3x12", R.drawable.podem_nog_v_vise_na_turnike)
       )

        listView.adapter = ExerciseAdapter(this, R.layout.row, exercisesList
        )

        listView.setOnItemClickListener { parent, view, position, id ->
            val descIntent = Intent(this, DescriptionActivity::class.java)
            when (position) {
                0 -> {
                           descIntent.apply {
                                putExtra("Name", "Становая тяга")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "4")
                                putExtra("Weight", "20")
                                putExtra("Picture", R.drawable.stanovaya)
                            }
                }
                1 -> {
                    descIntent.apply {
                                putExtra("Name", "Подтягивания широким хватом")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "4")
                                putExtra("Weight", "собственный вес")
                                putExtra("Picture", R.drawable.podtyagivaniya_shirokim_hvatom)
                            }
                }
                2 -> {
                    descIntent.apply {
                                putExtra("Name", "Тяга гантели в наклоне")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "18")
                                putExtra("Picture", R.drawable.tyaga_ganteli)
                            }
                }
                3 -> {
                    descIntent.apply {
                                putExtra("Name", "Тяга верхнего блока узким обратным хватом")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "25")
                                putExtra("Picture", R.drawable.tyaga_blocka)
                            }
                }
                4 -> {
                    descIntent.apply {
                                putExtra("Name", "Подъемы штанги на бицепс")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "15")
                                putExtra("Picture", R.drawable.shtanga_na_biceps)
                            }
                }
                5 -> {
                    descIntent.apply {
                                putExtra("Name", "Подъемы ног в висе")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "собственный вес")
                                putExtra("Picture", R.drawable.podem_nog_v_vise_na_turnike)
                            }
                }
            }
            descIntent.putExtra("day", "Wednesday")
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