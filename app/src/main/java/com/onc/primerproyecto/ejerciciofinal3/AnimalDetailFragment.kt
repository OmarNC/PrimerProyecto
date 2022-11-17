package com.onc.primerproyecto.ejerciciofinal3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.onc.primerproyecto.R
import com.onc.primerproyecto.ejerciciofinal3.SqlHelper
import com.onc.primerproyecto.ejerciciofinal3.Sexo

class AnimalDetailFragment : Fragment() {
    private lateinit var sqlHelper : SqlHelper

    //Elementos de la vista
    private lateinit var etSrc :EditText
    private lateinit var ivFoto : ImageView
    private lateinit var etNombre: EditText
    private lateinit var etDescripcion : EditText
    private lateinit var cbEnfermedad : CheckBox
    private lateinit var rgSexo : RadioGroup
    private lateinit var btnAceptar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sqlHelper = SqlHelper(this.requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_animal_detail, container, false)

        etSrc = view.findViewById<EditText>(R.id.etSrc)
        ivFoto = view.findViewById<ImageView>(R.id.imgFoto)
        etNombre = view.findViewById<EditText>(R.id.etNombre)
        etDescripcion = view.findViewById<EditText>(R.id.etDescripcion)
        cbEnfermedad = view.findViewById<CheckBox>(R.id.cbEnfermo)
        rgSexo = view.findViewById<RadioGroup>(R.id.rgSexo)
        btnAceptar = view.findViewById<Button>(R.id.btnAceptar)



        etSrc.setOnFocusChangeListener { view, tieneFoco ->
            //Toast.makeText(this.context, "El valor del estado del foco es: $b", Toast.LENGTH_SHORT).show()
            if (!tieneFoco) {
                actualizarImagen()
            }

        }


        btnAceptar.setOnClickListener { view

            val src = etSrc.text.toString()
            val nombre = etNombre.text.toString()
            val descripcion = etDescripcion.text.toString()
            val enfermedad = cbEnfermedad.isChecked
            val sexo : Sexo

           sexo =  when(rgSexo.checkedRadioButtonId){
                R.id.rbMacho -> Sexo.MACHO
                R.id.rbHembra ->Sexo.HEMBRA
                else -> Sexo.SIN_DEFINIR
            }
            val animal = AnimalSQLModel (0, nombre, src,  descripcion, enfermedad,  sexo)
            sqlHelper.insert(animal)

            this.parentFragmentManager.beginTransaction().remove(this).commit()
            this.activity?.finish()
        }



        return view
    }

    private fun actualizarImagen()
    {
            val src = etSrc.text.toString()
            if (src.isNotEmpty())
                Glide.with(this).load(src).into(ivFoto)
            else
                ivFoto.setImageResource(R.drawable.sound_detection_dog_barking_48px)

    }


}