package com.example.cft.retrofit.data

import com.google.gson.annotations.SerializedName

data class CountryX(
    @SerializedName("numeric") var numeric: String? = null,
    @SerializedName("alpha2") var alpha2: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("emoji") var emoji: String? = null,
    @SerializedName("currency") var currency: String? = null,
    @SerializedName("latitude") var latitude: Double? = null,
    @SerializedName("longitude") var longitude: Double? = null,
)