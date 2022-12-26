package com.example.cft.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BankingDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCard(bankingInformationModel: BankingInformationModel)

    @Query("SELECT * FROM bankingTable")
    fun getAllCards(): Flow<List<BankingInformationModel>>
}