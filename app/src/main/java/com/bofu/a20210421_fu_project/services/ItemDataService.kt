package com.bofu.a20210421_fu_project.services

import com.bofu.a20210421_fu_project.models.main.OverallData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ItemDataApi {
    @GET("ynap/v1/searchresult")
    fun getList(
        //@Header("authorization") auth: String,
        //@Query("limit") limit: Int,
        //@Query("offset") offset: Int
    ) : Call<OverallData>
}

class ItemDataService: BaseService() {
    private val api: ItemDataApi

    init {
        val retrofit = Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(
            GsonConverterFactory.create()).build()
        api = retrofit.create(ItemDataApi::class.java)
    }

    fun getList(callback: Callback<OverallData>/*, token: String, limit: Int, offset : Int*/){
        val call = api.getList(/*"Bearer $token", limit, offset*/)
        call.enqueue(callback)
    }
}