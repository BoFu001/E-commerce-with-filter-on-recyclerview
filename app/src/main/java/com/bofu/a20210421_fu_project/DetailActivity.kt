package com.bofu.a20210421_fu_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bofu.a20210421_fu_project.adapters.ItemAdapter
import com.bofu.a20210421_fu_project.extensions.getUrl
import com.bofu.a20210421_fu_project.viewModels.DetailViewModel
import com.bofu.a20210421_fu_project.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private val detailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        actionbarSetup()
        itemViewModelSetup()
        itemViewModelGetDetail()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun actionbarSetup(){
        // enable back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // title
        title = "Detail"
    }

    private fun itemViewModelSetup(){
        detailViewModel.liveData.observe(this, Observer {
            uiUpdate()
        })
    }

    private fun itemViewModelGetDetail(){
        if(!detailViewModel.isLoading.value!!){
            detailViewModel.getItemDetail()
        }
    }

    private fun uiUpdate(){
        val detail = detailViewModel.liveData.value!!
        val (cod10,brand,category,price,description,color,sizes) = detail

        val imageUrl = cod10.getUrl()
        detail_brandname_tv.text = brand.Name
        detail_categoryname_tv.text = category.Name
        detail_fullprice_tv.text = price.FullPrice
        detail_discountedprice_tv.text = price.DiscountedPrice
        detail_productinfo_tv.text = description.ProductInfo.toString()
    }


}