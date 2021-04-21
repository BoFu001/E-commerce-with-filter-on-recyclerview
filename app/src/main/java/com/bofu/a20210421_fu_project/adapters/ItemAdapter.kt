package com.bofu.a20210421_fu_project.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bofu.a20210421_fu_project.R
import com.bofu.a20210421_fu_project.extensions.getUrl
import com.bofu.a20210421_fu_project.models.ItemData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_item.view.*

class ItemAdapter (var item: ArrayList<ItemData>, val onClickListener: (ItemData, Int) -> Unit): RecyclerView.Adapter<ItemAdapter.OverallDataHolder>(){

    fun update(newData: ArrayList<ItemData>) {
        item.clear()
        item.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverallDataHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item,parent,false)
        return OverallDataHolder(view)
    }

    override fun getItemCount() =  item.size


    override fun onBindViewHolder(holder: OverallDataHolder, position: Int) {
        holder.brandName.text = item[position].Brand
        holder.category.text = item[position].MicroCategory

        holder.fullPrice.text = item[position].FormattedFullPrice
        holder.fullPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        if (item[position].FormattedFullPrice != item[position].FormattedDiscountedPrice){
            holder.fullPrice.visibility = View.VISIBLE
        }
        holder.discountedPrice.text = item[position].FormattedDiscountedPrice

        val imageUrl = item[position].Cod10.getUrl()
        Picasso.get().load(imageUrl).into(holder.productImg)

        holder.itemView.setOnClickListener {
            onClickListener(item[position], position)
        }
    }

    class OverallDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val productImg: ImageView = itemView.item_image_imageView
        val brandName: TextView = itemView.item_brandname_tv
        val category: TextView = itemView.item_category_tv
        val fullPrice: TextView = itemView.item_full_price_tv
        val discountedPrice: TextView = itemView.item_discounted_price_tv
    }


}