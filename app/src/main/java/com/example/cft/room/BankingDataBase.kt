package com.example.cft.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BankingInformationModel::class], version = 1)
abstract class BankingDataBase : RoomDatabase() {
    abstract fun getDao():BankingDAO

    companion object {
        @Volatile// делает объект видимым для всех потоков
        private var database: BankingDataBase? = null// определяем базу данных

        @Synchronized//защита от одновременного выполнения несколькими потоками
        fun getDataBase(context: Context): BankingDataBase { //  инициализируем БД
            return if (database == null) {
                database = Room.databaseBuilder(context, BankingDataBase::class.java, "bd")
                    .allowMainThreadQueries()
                    .build()
                database as BankingDataBase
            } else {
                database as BankingDataBase
            }
        }
    }
}