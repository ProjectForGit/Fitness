package com.example.fitness.ectomorph

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitness.R
import com.example.fitness.ectomorph.trainings.Friday
import com.example.fitness.ectomorph.trainings.Monday
import com.example.fitness.ectomorph.trainings.Wednesday
import com.example.fitness.fragments.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_second.*
import java.io.*


class EctomorphActivity : AppCompatActivity() {

    private val fileName = "kcal"
    private val kcalFile: File = File(fileName)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpTabs()

        val path = getFileStreamPath(fileName)

        if(path.exists())
        {
            val fis: FileInputStream = this.openFileInput(fileName)
            val os = ObjectInputStream(fis)
            val kcalValue: String = os.readObject() as String
            os.close()
            fis.close()
            val intent: Intent = intent.apply { putExtra("Kcal", kcalValue) }
        }

    }


    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(firstFragment(), "Тренировки")
        adapter.addFragment(secondFragment(), "Расчёт калорий")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

    }

    fun MonCLick(view: View)
    {
        val intent = Intent(this, Monday::class.java)
        startActivity(intent)
    }

    fun WedClick(view: View)
    {
        val intent = Intent(this, Wednesday::class.java)
        startActivity(intent)
    }
    fun FriClick(view: View)
    {
        val intent = Intent(this, Friday::class.java)
        startActivity(intent)
    }

    fun calc(view: View) {
        Calculation()
    }



    fun Calculation()
    {
        val heightText = heightPicker.value.toString().toInt()
        val weightText = weightPicker.value.toString().toInt()
        val ageText = agePicker.value.toString().toInt()
        var value = 0.0

        if(woman.isChecked)
        {
             value = (447.7 + (9.2 * weightText) + (3.1 * heightText) - (4.3 * ageText)) * 1.2
            kcal.setText(value.toInt().toString())
        }
        if (man.isChecked)
        {
             value = (88.36 + (13.4 * weightText) + (4.8 * heightText) - (5.7 * ageText)) * 1.5
            kcal.setText(value.toInt().toString())
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

    fun checkRadioButton()
    {
        if(woman.isChecked == false && man.isChecked == false)
            Toast.makeText(this, "Выберите пол", Toast.LENGTH_SHORT).show()
    }
}