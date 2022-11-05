package com.onc.primerproyecto.ejercicioclase.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.onc.primerproyecto.R

class TapsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taps)

        val tabs = findViewById<TabLayout>(R.id.idTab)
        tabs.addTab(tabs.newTab().setIcon(android.R.drawable.btn_plus).setText("Futball"))
        tabs.addTab(tabs.newTab().setText("Basquetball"))
        tabs.addTab(tabs.newTab().setText("Tennis"))
        tabs.tabGravity=TabLayout.GRAVITY_FILL
    }
}