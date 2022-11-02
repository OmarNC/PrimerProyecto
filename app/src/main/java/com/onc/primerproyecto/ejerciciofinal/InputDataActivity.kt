package com.onc.primerproyecto.ejerciciofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.onc.primerproyecto.Persona
import com.onc.primerproyecto.R
import com.onc.primerproyecto.Usuario
import com.onc.primerproyecto.ejercicioclase.ExplicitDetailActivity

class InputDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_data)

        var btnEnviar = findViewById <Button>(R.id.btn_Enviar)

        btnEnviar.setOnClickListener {
            var edNombre = findViewById<EditText>(R.id.edt_Nombre)
            var edApellido = findViewById<EditText>(R.id.edt_Apellido)
            var edAltura = findViewById<EditText>(R.id.edt_Altura)
            var edPeso = findViewById<EditText>(R.id.edt_Peso)

            var strNombre = edNombre.text.toString()
            var strApellido = edApellido.text.toString()
            var strPeso = edPeso.text.toString()
            var strEstatura = edAltura.text.toString()

            if(strNombre.isEmpty() || strApellido.isEmpty()) {
                Toast.makeText(this, "Nombre y Apellido no deben estar vacios", Toast.LENGTH_SHORT).show()
            }
            else
            {
                var peso: Double
                var altura: Double
                try {
                    peso = strPeso.toDouble()
                    altura = strEstatura.toDouble()
                }
                catch (e:Exception)
                {
                    peso = 0.0
                    altura = 0.0
                }

                val intent = Intent(this, ViewDataActivity::class.java).apply {

                    //Enviando un objeto a la actividad
                    var persona = Persona( strNombre, strApellido, peso, altura)
                    putExtra("KEY_PERSONA", persona)
                }
                startActivity(intent)
            }

        }
    }
}