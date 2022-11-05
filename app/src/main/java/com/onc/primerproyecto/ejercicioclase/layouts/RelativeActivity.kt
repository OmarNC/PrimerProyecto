package com.onc.primerproyecto.ejercicioclase.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import com.onc.primerproyecto.R


class RelativeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relative)

        var cbCredit = findViewById<CheckBox>(R.id.cbCredito)
        cbCredit.isChecked = true

        var btnSave = findViewById<Button>(R.id.btnEnviar)

        var rgGenero = findViewById<RadioGroup>(R.id.rgGenero)

        cbCredit.setOnCheckedChangeListener{componentButton, isChecked ->
            Toast.makeText(this, "isChecked: ${isChecked}", Toast.LENGTH_SHORT).show()
        }



        //RadioGroup
        rgGenero.check(R.id.rbFemenino) //Establece como seleccionado a esta opción
        rgGenero.setOnCheckedChangeListener{radioGroup, idRadioButton ->
            val rbSelected = when(idRadioButton)
            {
                R.id.rbMasculino -> "Masculino"
                R.id.rbFemenino -> "Femenino"
                R.id.rbNeutro -> "Neutro"
                else -> "Opción no definida"

            }
            Toast.makeText(this, "isChecked: ${rbSelected}", Toast.LENGTH_SHORT).show()

        }

        //Usando interfaces
      /*  rgGenero.setOnCheckedChangeListener(object :RadioGroup.OnCheckedChangeListener{

            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                TODO("Not yet implemented")
            }

        })*/



        //Spinner
        var spinner = findViewById<Spinner>(R.id.idSpinner)
        var datos = arrayListOf("Elemento 1","Elemento 2","Elemento 3","Elemento 4", "Elemento 5","Elemento 6","Elemento 7")
        var adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, datos)
        adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adaptador

        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, viw: View?, position: Int, id: Long) {

                var itemSelected2 = parent?.getItemIdAtPosition(position)
                var itemSelected = datos[position]

                Toast.makeText(this@RelativeActivity, "Item posicion: ${itemSelected}\n Elelmente seleccionado: ${itemSelected2}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }


        btnSave.setOnClickListener {
            val isCheck = cbCredit.isChecked
            val rbSelected = when(rgGenero.checkedRadioButtonId)
            {
                R.id.rbMasculino -> "Masculino"
                R.id.rbFemenino -> "Femenino"
                R.id.rbNeutro -> "Neutro"
                else -> "Opción no definida"

            }

            var itemSelected = spinner.selectedItemPosition


            //Toast.makeText(this, "isChecked: ${isCheck}", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "isChecked: ${isCheck} \n Género: ${rbSelected}\nPosicion elem:${itemSelected}", Toast.LENGTH_SHORT).show()

        }




    }
}