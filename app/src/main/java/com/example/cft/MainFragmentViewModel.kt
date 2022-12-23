package com.example.cft

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainFragmentViewModel : ViewModel() {

    val currentBin = MutableLiveData<BankingInformationModel>()


    fun binRequest(bin: String, context: Context) {
        val url = "https://lookup.binlist.net/$bin"

        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            com.android.volley.Request.Method.GET,
            url,
            { cardData ->
                Log.d("MyLog", "Res: $cardData")
                binDataUsage(cardData)
            },
            { error ->
                Log.d("MyLog", "Err: $error")
            }
        )
        queue.add(request)
    }

    private fun binDataUsage(cardData: String) {
        val jsonCardData = JSONObject(cardData)

        // try/catch для того, что б не зависить от форма json`а

        val bankingInformation = BankingInformationModel()

        try {
            bankingInformation.paymentSystem = jsonCardData.getString("scheme")
        }
        catch (_: JSONException) {}
        try {
            bankingInformation.cardType = jsonCardData.getString("type")
        }
        catch (_: JSONException) {}
        try {
            bankingInformation.country = jsonCardData.getJSONObject("country").getString("name")
        }
        catch (_: JSONException) {}

        try {
            bankingInformation.latitude =  jsonCardData.getJSONObject("country").getString("latitude")
        }
        catch (_: JSONException) {}

        try {
            bankingInformation.longitude =  jsonCardData.getJSONObject("country").getString("longitude")
        }
        catch (_: JSONException) {}

        try {
            bankingInformation.bankName = jsonCardData.getJSONObject("bank").getString("name")
        }
        catch (_: JSONException) {}
        try {
            bankingInformation.website = jsonCardData.getJSONObject("bank").getString("url")
        }
        catch (_: JSONException) {}
        try {
            bankingInformation.bankPhone =  jsonCardData.getJSONObject("bank").getString("phone")
        }
        catch (_: JSONException) {}

        currentBin.value = bankingInformation

    }

}
