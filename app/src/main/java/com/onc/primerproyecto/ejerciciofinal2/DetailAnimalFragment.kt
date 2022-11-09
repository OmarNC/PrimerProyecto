package com.onc.primerproyecto.ejerciciofinal2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.TextView
import com.onc.primerproyecto.R

class DetailAnimalFragment : Fragment() {

    var animal: AnimalItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            animal = it.getSerializable("ARG_ANIMAL_ITEM") as AnimalItem
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_animal, container, false)
        val tvNombre = view.findViewById<TextView>(R.id.tvNombre)
        val cbEnfermedad = view.findViewById<CheckBox>(R.id.cbEnfermo)
        val rgSexo = view.findViewById<RadioGroup>(R.id.rgSexo)

        tvNombre.text = animal?.name ?: "Sin Nombre"
        cbEnfermedad.isChecked = animal?.tieneEnfermedad?: false

        when(animal?.sexo){
            Sexo.MACHO -> rgSexo.check(R.id.rbMacho)
            Sexo.HEMBRA -> rgSexo.check(R.id.rbHembra)
            else -> rgSexo.check(R.id.rbMacho)
        }


        return view
    }

    companion object {

        fun newInstance(animal : AnimalItem) =
            DetailAnimalFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("ARG_ANIMAL_ITEM", animal)
                }
            }
    }
}