package com.bofu.a20210421_fu_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bofu.a20210421_fu_project.R
import com.bofu.a20210421_fu_project.extensions.rgbTOhex
import com.bofu.a20210421_fu_project.models.detail.Color
import kotlinx.android.synthetic.main.row_color.view.*

class ColorAdapter (var item: ArrayList<Color>, val onClickListener: (Int) -> Unit): RecyclerView.Adapter<ColorAdapter.ColorHolder>(){

    fun update(newData: ArrayList<Color>) {
        item.clear()
        item.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_color,parent,false)
        return ColorHolder(view)
    }

    override fun getItemCount() =  item.size


    override fun onBindViewHolder(holder: ColorHolder, position: Int) {

        holder.icon.setColorFilter(android.graphics.Color.parseColor(item[position].Rgb.rgbTOhex()))
        holder.name.text = item[position].Name

        when(item[position].isSelected){
            true -> holder.check.visibility = View.VISIBLE
            false -> holder.check.visibility = View.GONE
        }

        holder.itemView.color_mask_iv.setOnClickListener {
            onClickListener(position)
        }
    }

    class ColorHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val icon: ImageView = itemView.color_background_iv
        val name: TextView = itemView.color_name_tv
        val check: ImageView = itemView.color_icon_check
    }


}