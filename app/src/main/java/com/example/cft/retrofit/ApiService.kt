package com.example.cft.retrofit

import com.example.cft.retrofit.data.RetrofitModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
     @GET("{bin}")
    suspend fun getPathBinData(
         @Path("bin") bin: String
    ):RetrofitModel
}