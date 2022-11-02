package com.onc.primerproyecto.ejercicioclase

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.onc.primerproyecto.R

class ImplicitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)
        val boton = findViewById<Button>(R.id.btn_send)

        boton.setOnClickListener {
            val email = Intent(Intent.ACTION_SENDTO)
            email.data = Uri.parse("mailto:")
            email.putExtra(Intent.EXTRA_EMAIL, arrayListOf("micorreo@server.com"))
            email.putExtra(Intent.EXTRA_SUBJECT, "Aviso Urgente")
            email.putExtra(Intent.EXTRA_TEXT, "Cuerpo del mensaje")

            //startActivity(email)
            startActivity(Intent.createChooser(email, "Test"))
        }


        //startActivity(email)

    }
}