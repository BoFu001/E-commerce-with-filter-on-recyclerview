package com.bofu.a20210421_fu_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bofu.a20210421_fu_project.R
import com.bofu.a20210421_fu_project.models.detail.Size
import kotlinx.android.synthetic.main.row_size.view.*

class SizeAdapter (var item: ArrayList<Size>, val onClickListener: (Int) -> Unit): RecyclerView.Adapter<SizeAdapter.SizeHolder>(){

    fun update(newData: ArrayList<Size>) {
        item.clear()
        item.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_size,parent,false)
        return SizeHolder(view)
    }

    override fun getItemCount() =  item.size


    override fun onBindViewHolder(holder: SizeHolder, position: Int) {

        holder.name.text = item[position].Name

        when(item[position].isSelected){
            true -> holder.background.setImageResource(R.drawable.custom_background_selected)
            false -> holder.background.setImageResource(R.drawable.custom_background_unselected)
        }

        holder.itemView.setOnClickListener {
            onClickListener(position)
        }
    }

    class SizeHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.size_name_tv
        val background: ImageView = itemView.size_background_iv
    }


}