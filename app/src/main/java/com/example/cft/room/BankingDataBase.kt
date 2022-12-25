package com.example.cft.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [BankingInformationModel::class], version = 2)
abstract class BankingDataBase : RoomDatabase() {
    abstract fun getDao():BankingDAO

    companion object {
        @Volatile// делает объект видимым для всех потоков
        private var database: BankingDataBase? = null// определяем базу данных

        @Synchronized//защита от одновременного выполнения несколькими потоками
        fun getDataBase(context: Context): BankingDataBase { //  инициализируем БД
            return if (database == null) {
                database = Room.databaseBuilder(context, BankingDataBase::class.java, "bd")
                    .addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries()
                    .build()
                database as BankingDataBase
            } else {
                database as BankingDataBase
            }
        }
    }
}
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE bankingTable ADD COLUMN bin TEXT")
    }
}