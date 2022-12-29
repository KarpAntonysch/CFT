package com.example.cft.retrofit.data

import com.google.gson.annotations.SerializedName

data class NumberX(
    @SerializedName("length") var length: Int? = null,
    @SerializedName("luhn") var luhn: Boolean? = null,
)