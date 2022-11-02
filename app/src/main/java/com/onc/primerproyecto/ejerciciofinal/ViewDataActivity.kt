package com.onc.primerproyecto.ejerciciofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.onc.primerproyecto.Persona
import com.onc.primerproyecto.R
import com.onc.primerproyecto.Usuario

class ViewDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)

       // Toast.makeText(this, "Ha entrado en OnCreate ViewDataActivity", Toast.LENGTH_LONG).show()

        var txvNombre = findViewById<TextView>(R.id.txvNombre)
        var txvApellido = findViewById<TextView>(R.id.txvApellido)
        var txvIMC = findViewById<TextView>(R.id.txvIMC)
        var txvMensaje = findViewById<TextView>(R.id.txvMensaje)

        intent.extras?.let{
            if (it.containsKey("KEY_PERSONA")){
                val persona : Persona = it.getSerializable("KEY_PERSONA") as Persona
                txvNombre.text = persona.nombre
                txvApellido.text = persona.apellido

                var IMC : Double = persona.peso/(Math.pow(persona.estatura, 2.0))
                txvIMC.text = "IMC: ${Math.round(IMC*10.0)/10.0}"

                var strMensaje: String = ""
                if (IMC < 18.5)
                    strMensaje = "Delgadez"
                else if (IMC < 25)
                    strMensaje = "Normal"
                else {
                    var pesoIdeal = 25*Math.pow(persona.estatura, 2.0)
                    strMensaje = "Sobrepeso\nPara tu estatura\nDebes pesar menos de: ${Math.round(pesoIdeal*10.0)/10.0}"
                }

                txvMensaje.text = strMensaje
            }


        }?: showEmptyInfo()//caso NULL
    }

    private fun showEmptyInfo()
    {
        Toast.makeText(this, "No se ha recibido ningún dato para mostrar", Toast.LENGTH_LONG).show()
    }

}