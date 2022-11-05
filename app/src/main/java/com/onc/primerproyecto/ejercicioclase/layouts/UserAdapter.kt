package com.onc.primerproyecto.ejercicioclase.layouts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onc.primerproyecto.R

class UserAdapter(private val items: ArrayList<UserItem>, private val listener: RecyclerItemListener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(view:View): RecyclerView.ViewHolder(view){
            var name : TextView
            var image : ImageView
            init{
                name = view.findViewById(R.id.txtVIdUsuario)
                image = view.findViewById(R.id.idImgViewLogo)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var view  = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.name.text = items[position].name
      //  holder.image.src = items[position].srcImage
        holder.itemView.setOnClickListener{
            listener.OnItemSelected(items[position])
        }
    }

    override fun getItemCount(): Int {
       //Cuantos Items Se visualizaran
        return  items.size
    }
}