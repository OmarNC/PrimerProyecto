package com.onc.primerproyecto.ejercicioclase.almacenamiento

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.onc.primerproyecto.R

class PrefsActivity : AppCompatActivity() {
    private  lateinit var tvNombre : TextView
    private  lateinit var edNombre : EditText
    private  lateinit var btGuardar : Button
    private  lateinit var btBorrar : Button
    private  lateinit var btBuscar : Button

    private lateinit var prefs : SharedPreferences
    private val PREFRS = "com.onc.sharepreferences"
    private val SHARE_NAME = "share_name"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prefs)

        prefs = getSharedPreferences(PREFRS, Context.MODE_PRIVATE)

        tvNombre = findViewById(R.id.tvName)
        edNombre = findViewById(R.id.etName)
        btGuardar = findViewById(R.id.btGuardar)
        btBorrar = findViewById(R.id.btBorrar)
        btBuscar = findViewById(R.id.btBuscar)

        btGuardar.setOnClickListener {
            prefs.edit().putString(SHARE_NAME, edNombre.text.toString()).apply()
            configView()
        }
        btBorrar.setOnClickListener {
            prefs.edit().remove(SHARE_NAME).apply()
            configView()
        }

        configView()
    }
    private fun askInfo()
    {
        tvNombre.visibility = View.INVISIBLE
        btBorrar.visibility = View.INVISIBLE

        edNombre.visibility = View.VISIBLE
        btGuardar.visibility = View.VISIBLE
    }

    private fun showInfo()
    {
        tvNombre.visibility = View.VISIBLE
        btBorrar.visibility = View.VISIBLE
        edNombre.visibility = View.INVISIBLE
        btGuardar.visibility = View.INVISIBLE

        tvNombre.text = "Hola ${prefs.getString(SHARE_NAME, "")}"
    }


    private fun isInfoSaved() : Boolean
    {
         val myName = prefs.getString(SHARE_NAME, "")
        /*
        if (myName?.isNotEmpty() == true)
            return true
        else
            return false

         */
        //return prefs.contains(SHARE_NAME)
        return myName?.isNotEmpty() == true
    }

    private fun configView()
    {
        btBuscar.visibility=View.GONE
        if (isInfoSaved())
            showInfo()
        else
            askInfo()

    }

}