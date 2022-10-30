package com.example.billmanager.repository

import com.example.billmanager.db.BillManagerDatabase
import com.example.billmanager.entity.Quantity
import javax.inject.Inject

class QuantityRepository @Inject constructor(
    val db: BillManagerDatabase
) {
    suspend fun insertQuantity(quantity: Quantity): Long {
        return db.getQuantityDao().insertQuantity(quantity)
    }
}