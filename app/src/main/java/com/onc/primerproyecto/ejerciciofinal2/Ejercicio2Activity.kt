package com.onc.primerproyecto.ejerciciofinal2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onc.primerproyecto.R

class Ejercicio2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2)

        supportFragmentManager.beginTransaction()
            .add(R.id.idFrameContainer,ListAnimalFragment.newInstance(getData()), "ListFragment")
           // .addToBackStack("ListFragment")
            .commit()
    }

    private fun getData() : ArrayList<AnimalItem>
    {
        val lista = ArrayList<AnimalItem>()
        lista.add(AnimalItem("León", "", true, Sexo.MACHO))
        lista.add(AnimalItem("Tigre", "", false, Sexo.HEMBRA))
        lista.add(AnimalItem("Pato", "", false, Sexo.HEMBRA))
        lista.add(AnimalItem("Conejo", "", true, Sexo.HEMBRA))
        lista.add(AnimalItem("Castor", "", true, Sexo.MACHO))
        lista.add(AnimalItem("Gato", "", false, Sexo.MACHO))
        lista.add(AnimalItem("Lobo", "", false, Sexo.HEMBRA))
        lista.add(AnimalItem("Borrego", "", true, Sexo.MACHO))

        return lista
    }
}