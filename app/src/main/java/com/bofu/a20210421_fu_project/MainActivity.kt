package com.bofu.a20210421_fu_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bofu.a20210421_fu_project.adapters.ItemAdapter
import com.bofu.a20210421_fu_project.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val itemAdapter = ItemAdapter(ArrayList(), this::open)
    private val mainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemRecyclerViewSetup()
        itemViewModelSetup()
        itemViewModelGetList()
    }


    private fun itemRecyclerViewSetup(){
        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = itemAdapter
        }
    }

    private fun itemAdaptorUpdate(){
        itemAdapter.update(mainViewModel.liveData.value!!)
    }

    private fun itemViewModelSetup(){
        mainViewModel.liveData.observe(this, Observer {
            itemAdaptorUpdate()
        })
    }

    private fun itemViewModelGetList(){
        if(!mainViewModel.isLoading.value!!){
            mainViewModel.getItemList()
        }
    }


    private fun open(position: Int){
        //val itemData = mainViewModel.liveData.value!![position]
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }



}