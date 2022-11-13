package com.onc.primerproyecto.ejercicioclase.almacenamiento

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onc.primerproyecto.R

class UserSQLAdapter(private val items: ArrayList<UserSqlModel>, private val listener: RecyclerItemSQLListener) : RecyclerView.Adapter<UserSQLAdapter.UserViewHolder>() {
    class UserViewHolder(view:View): RecyclerView.ViewHolder(view){
        var id : TextView
        var name : TextView
        var description : TextView
        init{
            id = view.findViewById(R.id.tvId)
            name = view.findViewById(R.id.tvName)
            description = view.findViewById(R.id.tvDescripcion)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var view  = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_user_item, parent, false)
        return UserViewHolder(view)
    }


    fun updateItems(newItems:ArrayList<UserSqlModel>)
    {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.id.text = items[position].id.toString()
        holder.name.text = items[position].name
        holder.description.text = items[position].description

        holder.itemView.setOnClickListener{
            listener.OnItemSelected(items[position])
        }
    }

    override fun getItemCount(): Int {
       //Cuantos Items Se visualizaran
        return  items.size
    }
}