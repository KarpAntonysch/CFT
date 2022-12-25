package com.example.cft.room

import android.app.Application

class DBApplication:Application() {
    private val dataBase by lazy { BankingDataBase.getDataBase(this) }
    val bankingRepository by lazy { BankingRepository(dataBase.getDao()) }

}