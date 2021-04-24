package com.bofu.a20210421_fu_project.activities

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bofu.a20210421_fu_project.R
import com.bofu.a20210421_fu_project.adapters.ItemAdapter
import com.bofu.a20210421_fu_project.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.no_connection_bottom_view.*
import kotlinx.android.synthetic.main.no_connection_center_view.*

class MainActivity : BaseActivity() {

    private val itemAdapter = ItemAdapter(ArrayList(), this::open)
    private val mainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecyclerViewSetup()
        mainViewModelSetup()
        checkConnection(no_connection_center_view, noconnection_center_btn, this::mainViewModelGetList, FIRST_LOADING)
    }



    private fun mainRecyclerViewSetup(){
        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = itemAdapter
        }

        main_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager =  recyclerView.layoutManager as LinearLayoutManager?

                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == mainViewModel.liveData.value!!.size - 1) {
                    checkConnection(no_connection_bottom_view, noconnection_bottom_btn, this@MainActivity::mainViewModelGetList, SCROLL_LOADING)
                }
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == mainViewModel.liveData.value!!.size - 2) {
                    showNoConnectionView(false)
                }
            }
        })
    }

    private fun mainAdaptorUpdate(){
        itemAdapter.update(mainViewModel.liveData.value!!)
    }

    private fun mainViewModelSetup(){
        mainViewModel.liveData.observe(this, Observer {
            mainAdaptorUpdate()
        })
        mainViewModel.isFirstLoading.observe(this, Observer {
            showProgressBar(main_progressBar_center, it)
        })
        mainViewModel.isScrollLoading.observe(this, Observer {
            showProgressBar(main_progressBar_bottom, it)
        })
    }

    private fun mainViewModelGetList(loadingType: Int){
        if(!mainViewModel.isFirstLoading.value!!){
            mainViewModel.getItemList(loadingType)
        }
    }


    private fun open(){
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }



}