package com.example.cft

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainFragmentViewModel : ViewModel() {

    val currentBin = MutableLiveData<BankingInformationModel>()


    fun binRequest(bin:String,context:Context){
        val url = "https://lookup.binlist.net/$bin"

        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            com.android.volley.Request.Method.GET,
            url,
            {
                cardData -> Log.d("MyLog", "Res: $cardData")
                binDataUsage(cardData)
            },
            {
                error -> Log.d("MyLog", "Err: $error")
            }
        )
        queue.add(request)
    }

    private fun binDataUsage(cardData:String){
        val jsonCardData = JSONObject(cardData)
        val bankingInformation = BankingInformationModel(
            jsonCardData.getString("scheme"),
            jsonCardData.getString("type"),
            jsonCardData.getJSONObject("country").getString("name"),
            jsonCardData.getJSONObject("bank").getString("name"),
            jsonCardData.getJSONObject("bank").getString("url"),
            jsonCardData.getJSONObject("bank").getString("phone")
        )
        currentBin.value=bankingInformation
         Log.d("MyLog", "INFO: $bankingInformation")
    }
}