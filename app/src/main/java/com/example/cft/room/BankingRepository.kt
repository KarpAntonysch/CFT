package com.example.cft.room

import kotlinx.coroutines.flow.Flow

class BankingRepository(private val bankingDAO: BankingDAO) {
    suspend fun insertCard(bankingInformationModel: BankingInformationModel) {
        bankingDAO.insertCard(bankingInformationModel)
    }
    val getAllCards:Flow<List<BankingInformationModel>> = bankingDAO.getAllCards()
}