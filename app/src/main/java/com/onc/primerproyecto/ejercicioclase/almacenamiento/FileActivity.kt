package com.onc.primerproyecto.ejercicioclase.almacenamiento

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.onc.primerproyecto.R

class FileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        val edInfo = findViewById<EditText>(R.id.etInfo)
        val bntSave = findViewById<Button>(R.id.btSave)
        val ivPerson = findViewById<ImageView>(R.id.ivImagen)

        //Carga un recurso de imagen mediante una url
        Glide.with(this)
            .load("https://2.bp.blogspot.com/-rC7EkWvy2lY/VO4Ih3qDLII/AAAAAAAApuI/BcPSNyEaGaA/s1600/leon%2B(6).jpg")
            .centerCrop()
            //.fitCenter()
            .into(ivPerson)


        val fileName = "prueba.txt"
        val body = "Todo el Contenido del texto"

        resources.openRawResource(R.raw.ejemplo_raw).use {stream->
            val text = stream.bufferedReader().readText()
            Toast.makeText(this, "Contenido raw: ${text}", Toast.LENGTH_LONG).show()
        }

        bntSave.setOnClickListener {
            openFileOutput(fileName, Context.MODE_PRIVATE).use{ output ->
                //output.write(body.toByteArray())
                output.write(edInfo.text.toString().toByteArray())
            }

            openFileInput(fileName).use { stream ->
                val contenido = stream.bufferedReader().readText()
                Toast.makeText(this, "Contenido: ${contenido}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}