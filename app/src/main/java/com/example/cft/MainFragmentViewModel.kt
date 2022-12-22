package com.example.cft

import android.app.Application
import android.app.DownloadManager.Request
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainFragmentViewModel : ViewModel() {



    fun binRequest(bin:String,context:Context){
        val url = "https://lookup.binlist.net/$bin"

        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            com.android.volley.Request.Method.GET,
            url,
            {
                result -> Log.d("MyLog", "Res: $result")
            },
            {
                error -> Log.d("MyLog", "Err: $error")
            }
        )
        queue.add(request)
    }
}