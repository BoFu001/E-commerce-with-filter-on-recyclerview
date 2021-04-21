package com.bofu.a20210421_fu_project.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bofu.a20210421_fu_project.models.ItemData
import com.bofu.a20210421_fu_project.models.OverallData
import com.bofu.a20210421_fu_project.services.ItemDataService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {


    private val TAG = javaClass.simpleName
    private val itemDataService = ItemDataService()
    private var data = ArrayList<ItemData>()
    var liveData = MutableLiveData<ArrayList<ItemData>>()
    val isLoading = MutableLiveData<Boolean>(false)

    fun getItemList(){

        isLoading.value = true

        val callback = object : Callback<OverallData> {
            override fun onFailure(call: Call<OverallData>, t: Throwable) {
                Log.d(TAG, "On failure, error: ${t.message}")
                isLoading.value = false
            }

            override fun onResponse(
                call: Call<OverallData>,
                response: Response<OverallData>
            ) {
                Log.d(TAG, ": On response: ${response.code()}")
                isLoading.value = false
                if (response.code() == 200) {
                    val result = response.body()
                    result?.let {
                        //println("aaa" + it.Items.size)
                        data.addAll(it.Items)
                        liveData.value = data
                    }
                }
            }
        }
        itemDataService.getList(callback/*, token, 6, data.count()*/)
    }



}