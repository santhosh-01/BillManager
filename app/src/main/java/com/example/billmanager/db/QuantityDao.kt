package com.example.billmanager.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.billmanager.entity.Quantity

@Dao
interface QuantityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuantity(article: Quantity): Long

}