package com.example.cft.retrofit.data

import com.google.gson.annotations.SerializedName

data class BankX(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("city") var city: String? = null,
)