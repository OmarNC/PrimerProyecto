package com.onc.primerproyecto.ejerciciofinal2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.onc.primerproyecto.R

class AnimalAdapter(private  val items : ArrayList<AnimalItem>, private  val listener: RecyclerItemListener) :RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> (){

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvAnimal : TextView
        val ivImagen : ImageView

        init {
            tvAnimal = itemView.findViewById(R.id.tvAnimal)
            ivImagen = itemView.findViewById(R.id.idImg)
        }
    }

    //Crea el objeto que mantiene cada vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        var vista = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_animal, parent, false)
        return AnimalViewHolder(vista)
    }

    //Se debe establecer que objeto se visualizará en la vista
    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.tvAnimal.text  = items[position].name
        //holder.ivImagen.src = ""
        holder.itemView.setOnClickListener{
            listener.onItemSelected(items[position])
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

}