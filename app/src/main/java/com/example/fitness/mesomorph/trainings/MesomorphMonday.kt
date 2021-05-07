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

class MesomorphMonday : AppCompatActivity() {

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
        setContentView(R.layout.activity_mesomorph_monday)

        listView = findViewById(R.id.listView)
        exercisesList = arrayListOf(
            Exercise("Жим штанги на наклонной скамье", "4х10", R.drawable.zhim_verh),
            Exercise("Жим гантелей на горизонтальной скамье", "3х12", R.drawable.zhim_gantelei_lezha),
            Exercise("Отжимания на брусьях", "3х12", R.drawable.otjimaniya_na_brusyah),
            Exercise("Жим штанги лежа узким хватом", "3х10", R.drawable.jim_uzkim_hvatom),
            Exercise("Французский жим со штангой", "3х12", R.drawable.franzyskyi_zhim),
            Exercise("Жим гантелей сидя", "4х12", R.drawable.zhim_sidya),
            Exercise("Тяга штанги к подбородку широким хватом", "3х12", R.drawable.tyaga_k_podborodku)
        )


        listView.adapter = ExerciseAdapter(this, R.layout.row, exercisesList)

        listView.setOnItemClickListener { parent, view, position, id ->
            val descIntent = Intent(this, DescriptionActivity::class.java)
            when (position) {
                0 -> {
                    descIntent.apply {
                        putExtra("Name", "Жим штанги на наклонной скамье")
                        putExtra("Repeat", "10")
                        putExtra("Podhod", "4")
                        putExtra("Weight", "20")
                        putExtra("Picture", R.drawable.zhim_verh)
                    }
                }
                1 -> {
                    descIntent.apply {
                        putExtra("Name", "Жим гантелей на горизонтальной скамье")
                        putExtra("Repeat", "12")
                        putExtra("Podhod", "3")
                        putExtra("Weight", "50")
                        putExtra("Picture", R.drawable.zhim_gantelei_lezha)
                    }
                }
                2 -> {
                    descIntent.apply {
                        putExtra("Name", "Отжимания на брусьях")
                        putExtra("Repeat", "12")
                        putExtra("Podhod", "3")
                        putExtra("Weight", "собственный вес")
                        putExtra("Picture", R.drawable.otjimaniya_na_brusyah)
                    }
                }
                3 -> {
                    descIntent.apply {
                        putExtra("Name", "Жим штанги лежа узким хватом")
                        putExtra("Repeat", "10")
                        putExtra("Podhod", "3")
                        putExtra("Weight", "15")
                        putExtra("Picture", R.drawable.jim_uzkim_hvatom)
                    }
                }
                4 -> {
                    descIntent.apply {
                        putExtra("Name", "Французский жим со штангой")
                        putExtra("Repeat", "12")
                        putExtra("Podhod", "3")
                        putExtra("Weight", "10")
                        putExtra("Picture", R.drawable.franzyskyi_zhim)
                    }
                }
                5 -> {
                    descIntent.apply {
                        putExtra("Name", "Жим гантелей сидя")
                        putExtra("Repeat", "12")
                        putExtra("Podhod", "4")
                        putExtra("Weight", "14")
                        putExtra("Picture", R.drawable.zhim_sidya)
                    }
                }
                6 -> {
                    descIntent.apply {
                        putExtra("Name", "Тяга штанги к подбородку широким хватом")
                        putExtra("Repeat", "12")
                        putExtra("Podhod", "3")
                        putExtra("Weight", "25")
                        putExtra("Picture", R.drawable.tyaga_k_podborodku)
                    }
                }
            }
            descIntent.putExtra("day", "MesomorphMonday")
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