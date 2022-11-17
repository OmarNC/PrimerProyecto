package com.onc.primerproyecto.ejerciciofinal3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onc.primerproyecto.R
import com.onc.primerproyecto.ejercicioclase.fragments.FirstFragment

class AddAnimalsSQLiteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_animals_sqlite)

        supportFragmentManager.beginTransaction()
            .add(R.id.idFragment, AnimalDetailFragment(), "AnimalDetailFragment")
            .addToBackStack("AnimalDetailFragment")
            .commit()
    }
}