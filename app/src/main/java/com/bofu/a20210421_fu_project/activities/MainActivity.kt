package com.bofu.a20210421_fu_project.activities

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bofu.a20210421_fu_project.R
import com.bofu.a20210421_fu_project.adapters.ItemAdapter
import com.bofu.a20210421_fu_project.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.no_connection_view.*

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
        checkConnection(noconnection_retry_btn, this::mainViewModelGetList)
    }



    private fun mainRecyclerViewSetup(){
        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = itemAdapter
        }
    }

    private fun mainAdaptorUpdate(){
        itemAdapter.update(mainViewModel.liveData.value!!)
    }

    private fun mainViewModelSetup(){
        mainViewModel.liveData.observe(this, Observer {
            mainAdaptorUpdate()
        })
    }

    private fun mainViewModelGetList(){
        if(!mainViewModel.isLoading.value!!){
            mainViewModel.getItemList()
        }
    }


    private fun open(position: Int){
        // val itemData = mainViewModel.liveData.value!![position]
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }



}