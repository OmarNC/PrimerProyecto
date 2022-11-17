package com.onc.primerproyecto.ejerciciofinal3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.onc.primerproyecto.R

class AnimalAdapter(private  val items : ArrayList<AnimalSQLModel>,
                    private  val listener: RecyclerItemSQLListener) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> (){

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvId : TextView
        var tvName : TextView
        var tvDescription : TextView

        init{
            tvId = itemView.findViewById(R.id.tvIdAnimal)
            tvName = itemView.findViewById(R.id.tvNameAnimal)
            tvDescription = itemView.findViewById(R.id.tvDescripcionAnimal)
        }
    }

    fun updateItems(newItems:ArrayList<AnimalSQLModel>)
    {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    //Crea el objeto que mantiene cada vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        var vista = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_animal_item, parent, false)
        return AnimalViewHolder(vista)
    }

    //Se debe establecer que objeto se visualizará en la vista
    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.tvId.text  = items[position].id.toString()
        holder.tvName.text  = items[position].name
        holder.tvDescription.text  = items[position].descripcion
        //holder.ivImagen.src = ""
        holder.itemView.setOnClickListener{
            listener.OnItemSelected(items[position])

        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

}