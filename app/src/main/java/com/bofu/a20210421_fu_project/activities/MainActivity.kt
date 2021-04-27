package com.bofu.a20210421_fu_project.activities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bofu.a20210421_fu_project.R
import com.bofu.a20210421_fu_project.adapters.ItemAdapter
import com.bofu.a20210421_fu_project.adapters.ItemAdapter.Companion.BY_BRAND_NAME
import com.bofu.a20210421_fu_project.adapters.ItemAdapter.Companion.BY_CATEGORY
import com.bofu.a20210421_fu_project.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_header_recyclerview.*
import kotlinx.android.synthetic.main.filter_view.*
import kotlinx.android.synthetic.main.filter_view.view.*
import kotlinx.android.synthetic.main.no_connection_bottom_view.*
import kotlinx.android.synthetic.main.no_connection_center_view.*


class MainActivity : BaseActivity() {

    companion object {
        private const val AMPTY_KEYWORD = ""
    }

    private var query: String = AMPTY_KEYWORD
    private val itemAdapter = ItemAdapter(ArrayList(), this::openDetail, this::noResultView)
    private val mainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    var searchView: SearchView? = null
    var menuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionbarSetup()
        mainRecyclerViewSetup()
        filterToggleBtnSetup()
        filterCancelBtnSetup()
        mainViewModelSetup()
        checkConnection(main_no_connection_center_view, noconnection_center_btn, this::mainViewModelGetList, FIRST_LOADING)
        mainAdaptorChangeFilterMode(BY_BRAND_NAME)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_activity_main, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        menuItem = menu?.findItem(R.id.main_search)
        searchView = menuItem?.actionView as SearchView
        searchView?.queryHint = getString(R.string.main_searchview_by_brand_hint)
        searchView?.setSearchableInfo(manager.getSearchableInfo(componentName))

        /*
        searchView?.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {

            }
        }
        */

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                searchView?.clearFocus()
                searchView?.setQuery(AMPTY_KEYWORD, false)
                menuItem?.collapseActionView()

                text?.let{
                    query = it
                    mainAdaptorFilter()
                }
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                /*
                text?.let{
                    query = it
                    mainAdaptorFilter()
                }
                */
                return false
            }
        })
        return true
    }

    private fun actionbarSetup(){
        // delete shadow from toolbar
        supportActionBar?.elevation = 0f
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
                    checkConnection(main_no_connection_bottom_view, noconnection_bottom_btn, this@MainActivity::mainViewModelGetList, SCROLL_LOADING)
                }
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == mainViewModel.liveData.value!!.size - 2) {
                    noConnectionView(false)
                }
            }
        })
    }

    private fun mainAdaptorUpdate(){
        itemAdapter.update(mainViewModel.liveData.value!!)
    }

    private fun mainAdaptorChangeFilterMode(mode:Int){
        when(mode){
            BY_BRAND_NAME -> searchView?.queryHint = getString(R.string.main_searchview_by_brand_hint)
            BY_CATEGORY -> searchView?.queryHint = getString(R.string.main_searchview_by_category_hint)
        }
        itemAdapter.changeMode(mode)
    }

    private fun filterToggleBtnSetup(){
        recycler_header_filter_toggle_btn.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if(isChecked){
                when(checkedId){
                    R.id.filter_toggle_brand_btn -> {
                        mainAdaptorChangeFilterMode(BY_BRAND_NAME)
                        if (query.isNotEmpty()) mainAdaptorFilter()
                    }
                    R.id.filter_toggle_category_btn -> {
                        mainAdaptorChangeFilterMode(BY_CATEGORY)
                        if (query.isNotEmpty()) mainAdaptorFilter()
                    }
                }
            }
        }
    }

    private fun mainAdaptorFilter(){
        itemAdapter.filter.filter(query)
        filterTagView()
    }

    private fun filterCancelBtnSetup(){
        filter_view_btn.setOnClickListener {
            query = AMPTY_KEYWORD
            mainAdaptorFilter()
        }
    }

     private fun filterTagView(){
        val filterView = main_filter_view
        filterView.filter_view_tv.text = query
        when(query){
            AMPTY_KEYWORD -> {
                filterView.visibility = View.GONE
                //menuItem?.collapseActionView()
            }
            else -> filterView.visibility = View.VISIBLE
        }
    }

    private fun mainViewModelSetup(){
        mainViewModel.liveData.observe(this, Observer {
            mainAdaptorUpdate()
        })
        mainViewModel.isFirstLoading.observe(this, Observer {
            progressBar(main_progressBar_center, it)
        })
        mainViewModel.isScrollLoading.observe(this, Observer {
            progressBar(main_progressBar_bottom, it)
        })
    }

    private fun mainViewModelGetList(loadingType: Int){
        if(!mainViewModel.isFirstLoading.value!!){
            mainViewModel.getItemList(loadingType)
        }
    }


    private fun openDetail(){
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }

    private fun noResultView(show:Boolean){
        when(show){
            true -> main_no_result_view.visibility = View.VISIBLE
            false -> main_no_result_view.visibility = View.GONE
        }
    }

}