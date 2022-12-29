package com.example.cft.retrofit

import com.example.cft.retrofit.data.RetrofitModel

class Repository {

    suspend fun getPathBinData(bin:String):RetrofitModel{
        return RetrofitInstance.api.getPathBinData(bin)
    }
}