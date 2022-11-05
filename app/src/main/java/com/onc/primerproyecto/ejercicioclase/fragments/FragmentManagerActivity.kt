package com.onc.primerproyecto.ejercicioclase.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onc.primerproyecto.R

class FragmentManagerActivity : AppCompatActivity() {

    var name = "Omar Nieto"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_manager)

        supportFragmentManager.beginTransaction()
            .add(R.id.idFragment, FirstFragment.newInstance(name), "FirstFragment")
            .commit()
    }
}