package com.example.cft.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bankingTable")
data class BankingInformationModel(

    @ColumnInfo(name = "bin")
    var bin: String? = "Информация отсутсвует",

    @ColumnInfo(name = "paymentSystem")
    var paymentSystem: String? = "Информация отсутсвует",

    @ColumnInfo(name = "cardType")
    var cardType: String? = "Информация отсутсвует",

    @ColumnInfo(name = "country")
    var country: String? = "Информация отсутсвует",

    @ColumnInfo(name = "bankName")
    var bankName: String? = "Информация отсутсвует",

    @ColumnInfo(name = "website")
    var website: String? = "Информация отсутсвует",

    @ColumnInfo(name = "bankPhone")
    var bankPhone: String? = "Информация отсутсвует",

    @ColumnInfo(name = "latitude")
    var latitude: String? = "Информация отсутсвует",

    @ColumnInfo(name = "longitude")
    var longitude: String? = "Информация отсутсвует",
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

