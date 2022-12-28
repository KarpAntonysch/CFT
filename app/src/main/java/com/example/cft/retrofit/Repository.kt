package com.example.cft.retrofit

import com.android.volley.Response
import com.example.cft.retrofit.data.RetrofitData

class Repository {

    suspend fun getPathBinData(bin:String):Response<RetrofitData>{
        return RetrofitInstance.api.getPathBinData(bin)
    }
}