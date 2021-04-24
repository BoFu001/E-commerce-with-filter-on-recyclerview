package com.bofu.a20210421_fu_project.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bofu.a20210421_fu_project.activities.BaseActivity
import com.bofu.a20210421_fu_project.extensions.getOption
import com.bofu.a20210421_fu_project.models.main.ItemData
import com.bofu.a20210421_fu_project.models.main.OverallData
import com.bofu.a20210421_fu_project.services.ItemDataService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {


    private val TAG = javaClass.simpleName
    private val itemDataService = ItemDataService()
    private var data = ArrayList<ItemData>()
    var liveData = MutableLiveData<ArrayList<ItemData>>()
    val isFirstLoading = MutableLiveData<Boolean>(false)
    val isScrollLoading = MutableLiveData<Boolean>(false)
    private var index = 1

    private fun loading(loadingType:Int, bool: Boolean){
        when(loadingType){
            BaseActivity.FIRST_LOADING -> isFirstLoading.value = bool
            BaseActivity.SCROLL_LOADING -> isScrollLoading.value = bool
        }
    }

    fun getItemList(loadingType:Int){

        loading(loadingType,true)

        val callback = object : Callback<OverallData> {
            override fun onFailure(call: Call<OverallData>, t: Throwable) {
                Log.d(TAG, "On failure, error: ${t.message}")
                loading(loadingType,false)
            }

            override fun onResponse(
                call: Call<OverallData>,
                response: Response<OverallData>
            ) {
                Log.d(TAG, ": On response: ${response.code()}")
                loading(loadingType,false)
                if (response.code() == 200) {
                    val result = response.body()
                    result?.let {
                        data.addAll(it.Items)
                        liveData.value = data
                        index += 1
                    }
                }
            }
        }
        itemDataService.getList(callback, index.getOption())
    }



}