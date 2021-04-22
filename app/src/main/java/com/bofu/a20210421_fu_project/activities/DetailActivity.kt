package com.bofu.a20210421_fu_project.activities

import android.graphics.Paint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bofu.a20210421_fu_project.R
import com.bofu.a20210421_fu_project.extensions.format
import com.bofu.a20210421_fu_project.extensions.getUrl
import com.bofu.a20210421_fu_project.viewModels.DetailViewModel
import com.google.android.material.appbar.AppBarLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.custom_header.*
import kotlinx.android.synthetic.main.no_connection_view.*

class DetailActivity : BaseActivity() {

    private val detailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        actionbarSetup()
        collapsingTitleSetup()
        detailViewModelSetup()
        checkConnection(noconnection_retry_btn, this::detailViewModelGetDetail)
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

    private fun detailViewModelSetup(){
        detailViewModel.liveData.observe(this, Observer {
            uiUpdate()
        })
        detailViewModel.isLoading.observe(this, Observer {
            showProgressBar(header_progressBar,it)
        })
    }

    private fun detailViewModelGetDetail(){
        if(!detailViewModel.isLoading.value!!){
            detailViewModel.getItemDetail()
        }
    }

    private fun uiUpdate(){
        val detail = detailViewModel.liveData.value!!
        val (cod10,brand,category,price,description,colors,sizes) = detail

        val imageUrl = cod10.getUrl()
        Picasso.get().load(imageUrl).into(header_iv)

        detail_brandname_tv.text = brand.Name
        detail_categoryname_tv.text = category.Name
        detail_fullprice_tv.text = price.FullPrice
        detail_fullprice_tv.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        detail_discountedprice_tv.text = price.DiscountedPrice
        detail_productinfo_title_tv.text = "Highlights"


        detail_productinfo_tv.text = description.ProductInfo.format()

        detail_arrowup_lottie.playAnimation()


        println(colors.toString())
        println(sizes.toString())
    }


}