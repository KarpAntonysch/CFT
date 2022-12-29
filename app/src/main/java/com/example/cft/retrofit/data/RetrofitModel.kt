package com.example.cft.retrofit.data

import com.google.gson.annotations.SerializedName

data class RetrofitModel(
    @SerializedName("number") var number: NumberX? = NumberX(),
    @SerializedName("scheme") var scheme: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("brand") var brand: String? = null,
    @SerializedName("prepaid") var prepaid: Boolean? = null,
    @SerializedName("country") var country: CountryX? = CountryX(),
    @SerializedName("bank") var bank: BankX? = BankX(),

    )