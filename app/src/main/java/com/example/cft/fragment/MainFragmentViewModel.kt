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

class MainFragmentViewModel(private val repository: BankingRepository) : ViewModel() {
    val currentBin = MutableLiveData<BankingInformationModel>()

    fun binRequest(bin: String, context: Context) {
        val url = "https://lookup.binlist.net/$bin"

        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            com.android.volley.Request.Method.GET,
            url,
            { cardData ->
                Log.d("MyLog", "Res: $cardData")
                binDataUsage(cardData,bin)
            },
            { error ->
                if (error is NoConnectionError) {
                    Log.d("MyLog", "Err: Ошибка сети")
                    //TODO оповещение ошибка сети
                } else {
                    Log.d("MyLog", "Err:$error Ошибка сервера")
                    //TODO оповещение ошибка сервера
                }
            }
        )
        queue.add(request)
    }


    private fun binDataUsage(cardData: String,bin: String) {
        val jsonCardData = JSONObject(cardData)

        // try/catch для того, что б не зависить от форма json`а (так как сам сервер работает по разному: некоторые поля могут отсутсвовать, другие быть null)

        val bankingInformation = BankingInformationModel()

        bankingInformation.bin = bin

        try {
            bankingInformation.paymentSystem = jsonCardData.getString("scheme")
        } catch (_: JSONException) {
            bankingInformation.paymentSystem = "Информация отсутсвует"
        }
        try {
            bankingInformation.cardType = jsonCardData.getString("type")
        } catch (_: JSONException) {
            bankingInformation.cardType = "Информация отсутсвует"
        }
        try {
            bankingInformation.country = jsonCardData.getJSONObject("country").getString("name")
        } catch (_: JSONException) {
            bankingInformation.country = "Информация отсутсвует"
        }

        try {
            bankingInformation.latitude =
                jsonCardData.getJSONObject("country").getString("latitude")
        } catch (_: JSONException) {
            bankingInformation.latitude = "Информация отсутсвует"
        }

        try {
            bankingInformation.longitude =
                jsonCardData.getJSONObject("country").getString("longitude")
        } catch (_: JSONException) {
            bankingInformation.longitude = "Информация отсутсвует"
        }

        try {
            bankingInformation.bankName = jsonCardData.getJSONObject("bank").getString("name")
        } catch (_: JSONException) {
            bankingInformation.bankName = "Информация отсутсвует"
        }
        try {
            bankingInformation.website = jsonCardData.getJSONObject("bank").getString("url")
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

     val allCards:LiveData<List<BankingInformationModel>> = repository.getAllCards.asLiveData()
}
