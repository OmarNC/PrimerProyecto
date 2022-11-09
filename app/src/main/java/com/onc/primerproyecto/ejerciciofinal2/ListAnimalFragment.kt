package com.onc.primerproyecto.ejerciciofinal2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onc.primerproyecto.R

class ListAnimalFragment : Fragment(), RecyclerItemListener{

    var items = ArrayList<AnimalItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            items = it.getSerializable("ARG_ANIMALES") as ArrayList<AnimalItem>
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val  view = inflater.inflate(R.layout.fragment_animal_list, container, false)
        val lista = view.findViewById<RecyclerView>(R.id.rvAnimals)
        val animalsAdapter = AnimalAdapter(items,this)

        lista?.layoutManager = LinearLayoutManager(inflater.context, RecyclerView.VERTICAL, false)
        lista?.adapter = animalsAdapter

        return view
    }

    override fun onItemSelected(animal : AnimalItem){
        //Toast.makeText(context, "Nombre: ${animal.name}", Toast.LENGTH_SHORT).show()
        parentFragmentManager.beginTransaction()
            .add(R.id.idFrameContainer,DetailAnimalFragment.newInstance(animal), "DetailAnimalFragment")
            .addToBackStack("ListFragment")
            .commit()
    }

    //Función estática para crear el objeto con parámetros
    //-------------------------------------------------
    companion object {
        fun newInstance(items: ArrayList<AnimalItem>) =
            ListAnimalFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("ARG_ANIMALES", items)
                }
            }
    }


}