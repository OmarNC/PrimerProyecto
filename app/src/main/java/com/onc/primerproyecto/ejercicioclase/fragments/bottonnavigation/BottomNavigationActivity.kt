package com.onc.primerproyecto.ejercicioclase.fragments.bottonnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.onc.primerproyecto.R

class BottomNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        val menu = findViewById<BottomNavigationView>(R.id.menu)

        menu.selectedItemId=R.id.opcion_2
        LoadFragment( SegundoFragment())

        menu.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.opcion_1 ->
                {
                    LoadFragment( PrimerFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.opcion_2 ->
                {
                    LoadFragment( SegundoFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.opcion_3 ->
                {
                    LoadFragment( TercerFragment())
                    return@setOnItemSelectedListener true
                }
                else-> return@setOnItemSelectedListener false

            }
        }

    }

    private fun LoadFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction()
            .replace(R.id.root, fragment)
            .commit()
    }
}