package com.onc.primerproyecto.ejerciciofinal3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onc.primerproyecto.Persona
import com.onc.primerproyecto.R
import com.onc.primerproyecto.Usuario
import com.onc.primerproyecto.ejercicioclase.ExplicitDetailActivity
import com.onc.primerproyecto.ejerciciofinal1.ViewDataActivity
import com.onc.primerproyecto.ejerciciofinal2.ListAnimalFragment

class AnimalesSQLiteActivity : AppCompatActivity(), RecyclerItemSQLListener {

    private lateinit var sqlHelper: SqlHelper
    private lateinit var animalSQLAdapter: AnimalAdapter
    private lateinit var btAdd: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animales_sqlite)

        sqlHelper = SqlHelper(this)

        btAdd = findViewById<Button>(R.id.btn_Agregar)



        btAdd.setOnClickListener {
            val intent = Intent(this, AddAnimalsSQLiteActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        val lista = findViewById<RecyclerView>(R.id.rvAnimales)
        animalSQLAdapter = AnimalAdapter(sqlHelper.getAllAnimals(), this)
        lista.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        lista.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lista.itemAnimator = DefaultItemAnimator()
        lista.adapter = animalSQLAdapter

    }


    override fun OnItemSelected(animal: AnimalSQLModel) {
        val intent = Intent(this, AnimalShowDetailActivity::class.java).apply {
            //Enviando un objeto a la actividad
            putExtra("KEY_ANIMAL", animal)
        }
        startActivity(intent)

    }
}