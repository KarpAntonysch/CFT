package com.example.cft.retrofit

import com.android.volley.Response
import com.example.cft.retrofit.data.RetrofitData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

     @GET("{bin}")
    suspend fun getPathBinData(
         @Path("bin") bin: String
    ):Response<RetrofitData>
}