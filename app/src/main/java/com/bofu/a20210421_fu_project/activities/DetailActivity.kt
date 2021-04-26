package com.bofu.a20210421_fu_project.activities

import android.graphics.Paint
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bofu.a20210421_fu_project.R
import com.bofu.a20210421_fu_project.adapters.ColorAdapter
import com.bofu.a20210421_fu_project.adapters.SizeAdapter
import com.bofu.a20210421_fu_project.extensions.*
import com.bofu.a20210421_fu_project.viewModels.DetailViewModel
import com.google.android.material.appbar.AppBarLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.custom_header_collapse.*
import kotlinx.android.synthetic.main.no_connection_center_view.*


class DetailActivity : BaseActivity() {

    private var colorAdapter = ColorAdapter(ArrayList(), this::chooseColor)
    private var sizeAdapter = SizeAdapter(ArrayList(), this::chooseSize)
    private val detailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        actionbarSetup()
        collapsingTitleSetup()
        colorRecyclerViewSetup()
        sizeRecyclerViewSetup()
        detailViewModelSetup()
        checkConnection(header_no_connection_center_view, noconnection_center_btn, this::detailViewModelGetDetail, FIRST_LOADING)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun actionbarSetup(){

        // get toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.detail_toolbar_tab)

        // set actionbar
        setSupportActionBar(toolbar)

        // enable back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun collapsingTitleSetup(){

        var isShow = true
        var scrollRange = -1
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                detail_collapsing_toolbar_layout.title = "Product detail"
                isShow = true
            } else if (isShow){
                // careful there should a space between double quote otherwise it wont work
                detail_collapsing_toolbar_layout.title = " "
                isShow = false
            }
        })
    }

    private fun colorRecyclerViewSetup(){
        detail_color_recycler.apply {
            layoutManager = GridLayoutManager(context,1, GridLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = colorAdapter
        }
    }

    private fun sizeRecyclerViewSetup(){
        detail_size_recycler.apply {
            layoutManager = GridLayoutManager(context,1, GridLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = sizeAdapter
        }
    }

    private fun detailViewModelSetup(){
        detailViewModel.isDownloaded.observe(this, Observer {
            if(it) uiUpdate()
        })
        detailViewModel.isLoading.observe(this, Observer {
            progressBar(header_progressBar,it)
        })
        detailViewModel.isColorSelected.observe(this, Observer {
            if(it) colorRecyclerViewUpdate()
        })
        detailViewModel.isSizeSelected.observe(this, Observer {
            if(it) sizeRecyclerViewUpdate()
        })
    }

    private fun detailViewModelGetDetail(loadingType: Int){
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

        detail_color_title_tv.text = "Color"
        colorRecyclerViewUpdate()

        detail_size_title_tv.text = "Size"
        sizeRecyclerViewUpdate()

        animationSetup()
    }

    private fun animationSetup(){
        // adopt y coordinate dynamically
        val displayMetrics: DisplayMetrics = this.resources.displayMetrics
        val screenHeightPx = displayMetrics.heightPixels
        val nestedViewVisibleHeightPx = screenHeightPx - resources.getDimension(R.dimen.hearder_height)
        detail_arrowup_lottie.observerHeight {
            detail_arrowup_lottie.y = (nestedViewVisibleHeightPx - it)
        }

        // start animation
        detail_arrowup_lottie.playAnimation()
    }

    private fun colorRecyclerViewUpdate(){
        colorAdapter.update(detailViewModel.liveData.value!!.Colors)
    }

    private fun chooseColor(position:Int){
        detailViewModel.chooseColor(position)
    }

    private fun sizeRecyclerViewUpdate(){
        sizeAdapter.update(detailViewModel.liveData.value!!.Sizes)
    }

    private fun chooseSize(position: Int){
        detailViewModel.chooseSize(position)
    }

}