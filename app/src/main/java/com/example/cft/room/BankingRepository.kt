package com.example.cft.room

class BankingRepository(private val bankingDAO: BankingDAO) {
    suspend fun insertCard(bankingInformationModel: BankingInformationModel) {
        bankingDAO.insertCard(bankingInformationModel)
    }
}