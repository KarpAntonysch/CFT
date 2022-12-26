package com.example.cft.fragment

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.android.volley.NoConnectionError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.cft.room.BankingInformationModel
import com.example.cft.room.BankingRepository
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject

class MainFragmentViewModel(private val repository: BankingRepository,private val listener: CallBackListener) : ViewModel() {
    val currentBin = MutableLiveData<BankingInformationModel>()

    fun binRequest(bin: String, context: Context) {
        val url = "https://lookup.binlist.net/$bin"

        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            com.android.volley.Request.Method.GET,
            url,
            { cardData ->
                Log.d("MyLog", "Res: $cardData")
                binDataUsage(cardData, bin)
            },
            { error ->
                if (error is NoConnectionError) {
                    listener.openConnectionDialog()//callBack в MaimFragment
                } else {
                    Log.d("MyLog", "Err:$error Ошибка сервера")
                    listener.openServerDialog() // callBack в MainFragment
                }
            }
        )
        queue.add(request)
    }


    private fun binDataUsage(cardData: String, bin: String) {
        val jsonCardData = JSONObject(cardData)

        // try/catch для того, что б не зависить от форма json`а (так как сам сервер работает по разному: некоторые поля могут отсутсвовать, другие быть null)

        val bankingInformation = BankingInformationModel()

        bankingInformation.bin = bin

        try {
            val paymentSystem = jsonCardData.getString("scheme")
            if (paymentSystem == "null") {
                bankingInformation.paymentSystem = "Информация отсутсвует"
            } else {
                bankingInformation.paymentSystem = paymentSystem
            }
        } catch (_: JSONException) {
            bankingInformation.paymentSystem = "Информация отсутсвует"
        }

        try {
            val cardType = jsonCardData.getString("type")
            if (cardType == "null") {
                bankingInformation.cardType = "Информация отсутсвует"
            } else {
                bankingInformation.cardType = cardType
            }

        } catch (_: JSONException) {
            bankingInformation.cardType = "Информацияотсутсвует"
        }

        try {
            val country = jsonCardData.getJSONObject("country").getString("name")
            if (country =="null"){
                bankingInformation.country = "Информацияотсутсвует"
            }else{
                bankingInformation.country = country
            }
        } catch (_: JSONException) {
            bankingInformation.country = "Информация отсутсвует"
        }

        try {
            val latitude = jsonCardData.getJSONObject("country").getString("latitude")
            if(latitude == "null"){
                bankingInformation.latitude = "Информация отсутсвует"
            }else{
                bankingInformation.latitude =latitude
            }
        } catch (_: JSONException) {
            bankingInformation.latitude = "Информация отсутсвует"
        }

        try {
            val longitude = jsonCardData.getJSONObject("country").getString("longitude")
            if (longitude =="null"){
                bankingInformation.longitude = "Информация отсутсвует"
            }else{
                bankingInformation.longitude = longitude
            }
        } catch (_: JSONException) {
            bankingInformation.longitude = "Информация отсутсвует"
        }

        try {
            val bankName = jsonCardData.getJSONObject("bank").getString("name")
            if (bankName =="null"){
                bankingInformation.bankName = "Информация отсутсвует"
            } else{
                bankingInformation.bankName = bankName
            }
        } catch (_: JSONException) {
            bankingInformation.bankName = "Информация отсутсвует"
        }

        try {
            val website = jsonCardData.getJSONObject("bank").getString("url")
            if (website == "null"){
                bankingInformation.website = "Информация отсутсвует"
            }else{
                bankingInformation.website = website
            }
        } catch (_: JSONException) {
            bankingInformation.website = "Информация отсутсвует"
        }
        try {
            bankingInformation.bankPhone = jsonCardData.getJSONObject("bank").getString("phone")
        } catch (_: JSONException) {
            bankingInformation.bankPhone = "Информация отсутсвует"
        }
        currentBin.value = bankingInformation
    }

    fun insertCard(card: BankingInformationModel) = viewModelScope.launch {
        repository.insertCard(card)
    }

    val allCards: LiveData<List<BankingInformationModel>> = repository.getAllCards.asLiveData()
}
