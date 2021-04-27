package com.bofu.a20210421_fu_project.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bofu.a20210421_fu_project.R
import com.bofu.a20210421_fu_project.extensions.getUrl
import com.bofu.a20210421_fu_project.extensions.setSafeOnClickListener
import com.bofu.a20210421_fu_project.models.main.ItemData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class ItemAdapter (
        private val item: ArrayList<ItemData>,
        private val onClickListener: () -> Unit,
        private val onNoResultListener: (Boolean) -> Unit
): RecyclerView.Adapter<ItemAdapter.ItemDataHolder>(), Filterable {


    private var filteredItem = ArrayList<ItemData>()
    init {
        filteredItem = item
    }


    private var filterMode = 1
    companion object {
        const val BY_BRAND_NAME = 1
        const val BY_CATEGORY = 2
    }

    fun changeMode(mode: Int) {
        filterMode = mode
    }

    override fun getFilter(): Filter {

        return object : Filter() {


            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredItem = results?.values as ArrayList<ItemData>
                notifyDataSetChanged()

                when (filteredItem.size){
                    0 -> onNoResultListener(true)
                    else -> onNoResultListener(false)
                }
            }

            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val query = constraint.toString()
                if (query.isEmpty()) {
                    filteredItem = item
                } else {
                    filteredItem = item.filter { itemData: ItemData ->

                        val byBrandName = itemData.Brand.toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))
                        val byCategory = itemData.MicroCategory.toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))

                        when(filterMode){
                            BY_BRAND_NAME -> byBrandName
                            BY_CATEGORY -> byCategory
                            else -> true
                        }

                    } as ArrayList<ItemData>
                }
                val filterResults = FilterResults()
                filterResults.values = filteredItem
                return filterResults
            }
        }
    }


    fun update(newData: ArrayList<ItemData>) {
        item.clear()
        item.addAll(newData)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDataHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item,parent,false)
        return ItemDataHolder(view)
    }

    override fun getItemCount() =  filteredItem.size


    override fun onBindViewHolder(holder: ItemDataHolder, position: Int) {
        holder.brandName.text = filteredItem[position].Brand
        holder.category.text = filteredItem[position].MicroCategory

        holder.fullPrice.text = filteredItem[position].FormattedFullPrice
        holder.fullPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        when(filteredItem[position].FormattedFullPrice != filteredItem[position].FormattedDiscountedPrice){
            true -> holder.fullPrice.visibility = View.VISIBLE
            false -> holder.fullPrice.visibility = View.GONE
        }
        holder.discountedPrice.text = filteredItem[position].FormattedDiscountedPrice

        val imageUrl = filteredItem[position].Cod10.getUrl()
        Picasso.get().load(imageUrl).into(holder.productImg)

        holder.itemView.setSafeOnClickListener {
            onClickListener()
        }
    }

    class ItemDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val productImg: ImageView = itemView.item_image_iv
        val brandName: TextView = itemView.item_brandname_tv
        val category: TextView = itemView.item_category_tv
        val fullPrice: TextView = itemView.item_full_price_tv
        val discountedPrice: TextView = itemView.item_discounted_price_tv
    }




}