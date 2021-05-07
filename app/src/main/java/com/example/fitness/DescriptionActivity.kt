package com.example.fitness

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitness.ectomorph.trainings.Friday
import com.example.fitness.ectomorph.trainings.Monday
import com.example.fitness.ectomorph.trainings.Wednesday
import com.example.fitness.endomorph.trainings.EndomorphFriday
import com.example.fitness.endomorph.trainings.EndomorphMonday
import com.example.fitness.endomorph.trainings.EndomorphWednesday
import com.example.fitness.mesomorph.trainings.MesomorphFriday
import com.example.fitness.mesomorph.trainings.MesomorphMonday
import com.example.fitness.mesomorph.trainings.MesomorphWednesday
import kotlinx.android.synthetic.main.activity_description.*
import kotlinx.android.synthetic.main.fragment_first.*

class DescriptionActivity : AppCompatActivity() {

    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        val extras = intent.extras
        val Name = extras!!.getString("Name")
        val Repeat = extras!!.getString("Repeat")
        val Podhod = extras!!.getString("Podhod")
        val Picture = extras!!.getInt("Picture")
        val Weight = extras!!.getString("Weight")

        name.setText(Name)
        repeat.setText(Repeat)
        podhod.setText(Podhod)
        weight.setText("Рекомендуемый вес(кг): " + Weight)
        image.setImageResource(Picture)

    }

    fun startButtonClick(view: View) {
        val extras = intent.extras
        val day = extras!!.getString("day")

        when (day) {
            "Monday" -> Monday.exercisesList[intent.getIntExtra("pos", -1)].completed = true
            "Friday" -> Friday.exercisesList[intent.getIntExtra("pos", -1)].completed = true
            "Wednesday" -> Wednesday.exercisesList[intent.getIntExtra("pos", -1)].completed = true
            "MesomorphMonday" -> MesomorphMonday.exercisesList[intent.getIntExtra("pos", -1)].completed = true
            "MesomorphWednesday" -> MesomorphWednesday.exerciseList[intent.getIntExtra("pos", -1)].completed = true
            "MesomorphFriday" -> MesomorphFriday.exercisesList[intent.getIntExtra("pos", -1)].completed = true
            "EndomorphMonday" -> EndomorphMonday.exercisesList[intent.getIntExtra("pos", -1)].completed = true
            "EndomorphWednesday" -> EndomorphWednesday.exercisesList[intent.getIntExtra("pos", -1)].completed = true
            "EndomorphFriday" -> EndomorphFriday.exercisesList[intent.getIntExtra("pos", -1)].completed = true
        }
        Toast.makeText(this, "Упражнение выполнено", Toast.LENGTH_SHORT).show()
    }
}