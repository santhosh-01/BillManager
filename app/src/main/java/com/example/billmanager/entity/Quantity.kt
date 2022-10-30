package com.example.billmanager.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity(
    tableName = "quantity"
)
data class Quantity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val joined_date: OffsetDateTime,
    var quantity: Double
)
