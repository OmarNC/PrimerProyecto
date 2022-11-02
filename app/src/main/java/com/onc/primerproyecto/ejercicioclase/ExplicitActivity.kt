package com.onc.primerproyecto.ejercicioclase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.onc.primerproyecto.R
import com.onc.primerproyecto.Usuario

class ExplicitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        val btnEnviar=findViewById<Button>(R.id.btnEnviar)
        val edtName=findViewById<EditText>(R.id.edtName)



        btnEnviar.setOnClickListener {

            var strNombre = edtName.text.toString()

            if(strNombre.isEmpty()) {
                 Toast.makeText(this, "Nombre vacio", Toast.LENGTH_SHORT).show()
             }
            else
            {
                val intent = Intent(this, ExplicitDetailActivity::class.java).apply {
                    putExtra("KEY_NAME", "Omar")
                    putExtra("KEY_LASTNAME", "Nieto")
                    putExtra("KEY_AGE", 47)

                    //Usando un objeto
                    val usuario = Usuario("Juan", "Apellido de Juan", 30)
                    putExtra("KEY_USER", usuario)

                    //Usnaod un texto mediante un EditText

                    putExtra("KEY_ET_NAME", strNombre)

                }
                startActivity(intent)
            }



        }
    }
}