package com.onc.primerproyecto.ejercicioclase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.onc.primerproyecto.R
import com.onc.primerproyecto.Usuario

class ExplicitDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit_detail)

        var tvName=findViewById<TextView>(R.id.tvName)
        var tvLastName=findViewById<TextView>(R.id.tvLastName)
        var tvAge=findViewById<TextView>(R.id.tvAge)

       // supportActionBar?.

        intent.extras?.let{
            if (it.containsKey("KEY_NAME")){
                val name = it.getString("KEY_NAME")
                tvName.text = name
            }
            if (it.containsKey("KEY_LASTNAME")){
                val apellido = it.getString("KEY_LASTNAME")
                tvLastName.text = apellido
            }
            if (it.containsKey("KEY_AGE")){
                val edad = it.getInt("KEY_AGE")
                tvAge.text = edad.toString()
            }

            if (it.containsKey("KEY_USER")){
                val usuario : Usuario = it.getSerializable("KEY_USER") as Usuario
                tvName.text = usuario.name
                tvLastName.text = usuario.lastName
                tvAge.text = "${usuario.age}"
                //tvAge.text = usuario.age.toString()
            }

            if (it.containsKey("KEY_ET_NAME")){
                val etName  = it.getString("KEY_ET_NAME")
               // tvName.text = etName

                Toast.makeText(this, etName, Toast.LENGTH_LONG).show()
            }

        }?: showEmptyInfo()//caso NULL
    }

    private fun showEmptyInfo()
    {
        Toast.makeText(this, "Extras vacia", Toast.LENGTH_SHORT).show()
    }
}