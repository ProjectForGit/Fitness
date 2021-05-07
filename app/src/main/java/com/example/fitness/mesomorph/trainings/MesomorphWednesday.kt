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
import com.example.fitness.ectomorph.trainings.Monday
import com.example.fitness.ectomorph.trainings.Wednesday
import kotlinx.android.synthetic.main.activity_monday.*

class MesomorphWednesday : AppCompatActivity() {

    companion object
    {
        lateinit var exerciseList: ArrayList<Exercise>
        var buttonState = false
    }

    lateinit var listView: ListView
    var on: Boolean = false
    var time = ""
    private var exercisesValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesomorph_wednesday)

        listView = findViewById(R.id.listView)
        exerciseList = arrayListOf(
            Exercise("Становая тяга", "4х12", R.drawable.stanovaya),
            Exercise("Подтягивания широким хватом", "4х12", R.drawable.podtyagivaniya_shirokim_hvatom),
            Exercise("Тяга штанги в наклоне", "3х10", R.drawable.tyaga_k_poyasu),
            Exercise("Тяга вертикального блока узким обратным хватом", "3х12", R.drawable.tyaga_blocka),
            Exercise("Горизонтальная тяга", "3х12", R.drawable.gorizontalnaya_tyaga),
            Exercise("Подъемы гантелей на бицепс сидя на наклонной скамье", "4х10", R.drawable.bitseps_ganteli),
            Exercise("Махи гантелями в наклоне", "4х15", R.drawable.mahi_v_naklone)
        )

        listView.adapter = ExerciseAdapter(this, R.layout.row, exerciseList)

        listView.setOnItemClickListener { parent, view, position, id ->
            val descIntent = Intent(this, DescriptionActivity::class.java)
            when (position) {
                0 -> {

                    descIntent.apply {
                                putExtra("Name", "Становая тяга")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "25")
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
                                putExtra("Name", "Тяга штанги в наклоне")
                                putExtra("Repeat", "10")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "15")
                                putExtra("Picture", R.drawable.tyaga_k_poyasu)
                    }
                }
                3 -> {
                    descIntent.apply {
                                putExtra("Name", "Тяга вертикального блока узким обратным хватом")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "25")
                                putExtra("Picture", R.drawable.tyaga_blocka)
                    }
                }
                4 -> {
                    descIntent.apply {
                                putExtra("Name", "Горизонтальная тяга")
                                putExtra("Repeat", "12")
                                putExtra("Podhod", "3")
                                putExtra("Weight", "25")
                                putExtra("Picture", R.drawable.gorizontalnaya_tyaga)
                    }
                }
                6 -> {
                    descIntent.apply {
                                putExtra("Name", "Махи гантелями в наклоне")
                                putExtra("Repeat", "15")
                                putExtra("Podhod", "4")
                                putExtra("Weight", "10")
                                putExtra("Picture", R.drawable.mahi_v_naklone)
                    }
                }
            }
            descIntent.putExtra("day", "MesomorphWednesday")
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
        for (exercise in exerciseList) {
            exercisesValue++
            if (exercise.completed) {
                completedExercisesCount++
                exercise.completed = false
            }
        }
        return completedExercisesCount
    }
}