package com.bofu.a20210421_fu_project.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bofu.a20210421_fu_project.models.detail.ItemDetailData
import com.bofu.a20210421_fu_project.models.main.ItemData
import com.bofu.a20210421_fu_project.services.DetailDataService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel()  {

    private val TAG = javaClass.simpleName
    private val detailDataService = DetailDataService()
    private lateinit var data: ItemDetailData
    val liveData = MutableLiveData<ItemDetailData>()
    val isLoading = MutableLiveData<Boolean>(false)
    val isDownloaded = MutableLiveData<Boolean>()
    val isColorSelected = MutableLiveData<Boolean>()
    val isSizeSelected = MutableLiveData<Boolean>()

    fun getItemDetail(){

        isLoading.value = true

        val callback = object : Callback<ItemDetailData> {
            override fun onFailure(call: Call<ItemDetailData>, t: Throwable) {
                Log.d(TAG, "On failure, error: ${t.message}")
                isLoading.value = false
            }

            override fun onResponse(
                call: Call<ItemDetailData>,
                response: Response<ItemDetailData>
            ) {
                Log.d(TAG, ": On response: ${response.code()}")
                isLoading.value = false
                if (response.code() == 200) {
                    val result = response.body()
                    result?.let {
                        data = it
                        liveData.value = data
                        isDownloaded.value = true
                    }
                }
            }
        }
        detailDataService.getDetail(callback)
    }

    fun chooseColor(position:Int){
        data.Colors.map{
            it.isSelected = false
        }
        data.Colors[position].isSelected = true
        liveData.value = data

        isColorSelected.value = true
    }

    fun chooseSize(position:Int){
        data.Sizes.map{
            it.isSelected = false
        }
        data.Sizes[position].isSelected = true
        liveData.value = data

        isSizeSelected.value = true
    }

}