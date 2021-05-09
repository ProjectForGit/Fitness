package com.example.fitness.mesomorph

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitness.R
import com.example.fitness.ectomorph.secondFragment
import com.example.fitness.fragments.adapters.ViewPagerAdapter
import com.example.fitness.mesomorph.trainings.MesomorphFriday
import com.example.fitness.mesomorph.trainings.MesomorphMonday
import com.example.fitness.mesomorph.trainings.MesomorphWednesday
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_second.*
import java.io.*

class MesomorphActivity : AppCompatActivity() {

    private val fileName = "kcal"
    private val kcalFile: File = File(fileName)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesomorph_activity)


        setUpTabs()


        val path = getFileStreamPath(fileName)

        if (path.exists()) {
            val fis: FileInputStream = this.openFileInput(fileName)
            val os = ObjectInputStream(fis)
            val kcalValue: String = os.readObject() as String
            os.close()
            fis.close()
            val intent: Intent = intent.apply { putExtra("Kcal", kcalValue) }
        }
    }


    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Mesomorph_first_fragment(), "Тренировки")
        adapter.addFragment(secondFragment(), "Расчёт калорий")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        //tabs.getTabAt(0)!!.set
    }


    fun MonCLick(view: View) {
        var intent = Intent(this, MesomorphMonday::class.java)
        startActivity(intent)
    }

    fun WedClick(view: View) {
        var intent = Intent(this, MesomorphWednesday::class.java)
        startActivity(intent)
    }

    fun FriClick(view: View) {
        var intent = Intent(this, MesomorphFriday::class.java)
        startActivity(intent)
    }

    fun calc(view: View) {
        Calculation()
    }

    fun Calculation() {
        val heightText = heightPicker.value.toString().toInt()
        val weightText = weightPicker.value.toString().toInt()
        val ageText = agePicker.value.toString().toInt()
        val spinner: Spinner = findViewById(R.id.spinner)
        var value = 0.0
        val sportsmanTarget = spinner.selectedItem.toString()

        if(sportsmanTarget == "Набор мышечной массы")
        {
            if (woman.isChecked) {
                value = (447.7 + (9.2 * weightText) + (3.1 * heightText) - (4.3 * ageText)) * 1.3
                kcal.setText(value.toInt().toString())
            }
            if (man.isChecked) {
                value = (88.36 + (13.4 * weightText) + (4.8 * heightText) - (5.7 * ageText)) * 1.6
                kcal.setText(value.toInt().toString())
            }
        }
        else if (sportsmanTarget == "Похудение")
        {
            if (woman.isChecked) {
                value = (447.7 + (9.2 * weightText) + (3.1 * heightText) - (4.3 * ageText)) * 1.15
                kcal.setText(value.toInt().toString())
            }
            if (man.isChecked) {
                value = (88.36 + (13.4 * weightText) + (4.8 * heightText) - (5.7 * ageText)) * 1.3
                kcal.setText(value.toInt().toString())
            }
        }


        val fos: FileOutputStream = this.openFileOutput(fileName, Context.MODE_PRIVATE)
        val os = ObjectOutputStream(fos)
        os.writeObject(value.toInt().toString())
        os.close()
        fos.close()
        checkRadioButton()
    }

    override fun onBackPressed() {
        // do nothing
    }

    fun checkRadioButton() {
        if (woman.isChecked == false && man.isChecked == false)
            Toast.makeText(this, "Выберите пол", Toast.LENGTH_SHORT).show()
    }

}