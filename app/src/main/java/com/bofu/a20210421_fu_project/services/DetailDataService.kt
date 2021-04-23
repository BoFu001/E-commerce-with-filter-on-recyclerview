package com.bofu.a20210421_fu_project.services

import com.bofu.a20210421_fu_project.models.detail.ItemDetailData
import com.bofu.a20210421_fu_project.models.main.OverallData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface DetailDataApi {
    @GET("ynap/v1/item")
    fun getDetail() : Call<ItemDetailData>
}

class DetailDataService: BaseService() {
    private val api: DetailDataApi

    init {
        val retrofit = Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(
            GsonConverterFactory.create()).build()
        api = retrofit.create(DetailDataApi::class.java)
    }

    fun getDetail(callback: Callback<ItemDetailData>){
        val call = api.getDetail()
        call.enqueue(callback)
    }
}