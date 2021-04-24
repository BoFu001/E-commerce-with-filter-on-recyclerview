package com.bofu.a20210421_fu_project.services

import com.bofu.a20210421_fu_project.models.main.OverallData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ItemDataApi {
    @GET("ynap/v1/searchresult")
    fun getList1() : Call<OverallData>

    @GET("ynap/v1/searchresult2")
    fun getList2() : Call<OverallData>

    @GET("ynap/v1/searchresult3")
    fun getList3() : Call<OverallData>
}

class ItemDataService: BaseService() {
    private val api: ItemDataApi

    init {
        val retrofit = Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(
            GsonConverterFactory.create()).build()
        api = retrofit.create(ItemDataApi::class.java)
    }

    fun getList(callback: Callback<OverallData>, option:Int){

        lateinit var call: Call<OverallData>
        when(option){
            1 -> call = api.getList1()
            2 -> call = api.getList2()
            3 -> call = api.getList3()
        }
        call.enqueue(callback)
    }
}