package com.onc.primerproyecto.ejercicioclase.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onc.primerproyecto.R

class RecycleActivity : AppCompatActivity(), RecyclerItemListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle)

        val lista = findViewById<RecyclerView>(R.id.idLista)
        var userAdapter = UserAdapter(getData(), this)

        lista.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
       // lista.layoutManager = GridLayoutManager(this, 2)

        lista.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lista.itemAnimator = DefaultItemAnimator()

        lista.adapter = userAdapter


    }

    private fun getData() : ArrayList<UserItem>
    {
        var data = arrayListOf<UserItem>()
        data.add(UserItem("José", ""))
        data.add(UserItem("María", ""))
        data.add(UserItem("Beto", ""))
        data.add(UserItem("Juan", ""))
        data.add(UserItem("Rosa", ""))
        data.add(UserItem("Fer", ""))
        data.add(UserItem("Rosa", ""))
        data.add(UserItem("Fer", ""))
        data.add(UserItem("Rosa", ""))
        data.add(UserItem("Fer", ""))
        return data

    }

    override fun OnItemSelected(usuario: UserItem) {
        Toast.makeText(this, "Elemento seleccionado: ${usuario.name}", Toast.LENGTH_SHORT).show()
    }
}