package com.bofu.a20210421_fu_project

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bofu.a20210421_fu_project.extensions.format
import com.bofu.a20210421_fu_project.extensions.getUrl
import com.bofu.a20210421_fu_project.viewModels.DetailViewModel
import com.google.android.material.appbar.AppBarLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.custom_header.*

class DetailActivity : AppCompatActivity() {

    private val detailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        actionbarSetup()
        collapsingTitleSetup()
        itemViewModelSetup()
        itemViewModelGetDetail()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun actionbarSetup(){

        // get toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarTab)

        // set actionbar
        setSupportActionBar(toolbar)

        // enable back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun collapsingTitleSetup(){

        var isShow = true
        var scrollRange = -1
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                collapsingToolbarLayout.title = "Product detail"
                isShow = true
            } else if (isShow){
                // careful there should a space between double quote otherwise it wont work
                collapsingToolbarLayout.title = " "
                isShow = false
            }
        })
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
        Picasso.get().load(imageUrl).into(header_iv)

        detail_brandname_tv.text = brand.Name
        detail_categoryname_tv.text = category.Name
        detail_fullprice_tv.text = price.FullPrice
        detail_fullprice_tv.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        detail_discountedprice_tv.text = price.DiscountedPrice
        detail_productinfo_title_tv.text = "Highlights"


        detail_productinfo_tv.text = description.ProductInfo.format()
    }


}