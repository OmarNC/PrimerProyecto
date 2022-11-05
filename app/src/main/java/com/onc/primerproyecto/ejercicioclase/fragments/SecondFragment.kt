package com.onc.primerproyecto.ejercicioclase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.onc.primerproyecto.R
class SecondFragment : Fragment() {
    var name : String? = null

    //Función estática
    companion object{
        fun newInstance(name :String):SecondFragment{
            return SecondFragment().apply {
                arguments = Bundle().apply {
                    putString("ARG_NAME", name)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            name = it.getString("ARG_NAME")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_second, container, false)

        var tv = view.findViewById<TextView>(R.id.tvName)
        tv.text = name
        return view
    }
}