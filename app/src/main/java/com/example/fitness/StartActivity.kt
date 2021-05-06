package com.example.fitness

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.fitness.ectomorph.EctomorphActivity
import com.example.fitness.endomorph.EndomorphActivity
import com.example.fitness.mesomorph.MesomorphActivity
import kotlinx.android.synthetic.main.activity_start.*
import java.io.*

class StartActivity : AppCompatActivity() {

    private val fileName = "type"
    private  val typeFile: File = File(fileName)
    var radioButtonValue = ""
    var typeValue = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val path = getFileStreamPath(fileName)


        if(path.exists())
            deserialize()

    }

    fun enterMain(view: View)
    {
        if(ectomorph.isChecked)
        {
            val intent = Intent(this, EctomorphActivity::class.java)
            startActivity(intent)
            radioButtonValue = "1"
            serialize()
        }
        if(mesomorph.isChecked)
        {
            val intent = Intent(this, MesomorphActivity::class.java)
            startActivity(intent)
            radioButtonValue = "2"
            serialize()
        }
        if (endomorph.isChecked)
        {
            val intent = Intent(this, EndomorphActivity::class.java)
            startActivity(intent)
            radioButtonValue = "3"
            serialize()
        }
        else if (endomorph.isChecked == false && mesomorph.isChecked == false && ectomorph.isChecked == false)
        {
            Toast.makeText(this, "Выберите тип своего телосложения", Toast.LENGTH_LONG).show()
        }
    }

    fun serialize()
    {
        val fos: FileOutputStream = this.openFileOutput(fileName, Context.MODE_PRIVATE)
        val os = ObjectOutputStream(fos)
        os.writeObject(radioButtonValue)
        os.close()
        fos.close()
    }

    fun deserialize()
    {
        val fis: FileInputStream = this.openFileInput(fileName)
        val os = ObjectInputStream(fis)
        val typeValue: String = os.readObject() as String
        os.close()
        fis.close()

        if(typeValue == "1")
        {
            val intent = Intent(this, EctomorphActivity::class.java)
            startActivity(intent)
        }
        else if(typeValue == "2")
        {
            val intent = Intent(this, MesomorphActivity::class.java)
            startActivity(intent)
        }
        else if(typeValue == "3")
        {
            val intent = Intent(this, EndomorphActivity::class.java)
            startActivity(intent)
        }
    }
}