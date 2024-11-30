package com.hussein.mahashin.core.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hussein.mahashin.core.data.BookTypeConverter
import kotlinx.serialization.Serializable

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val quantity: Int,
    val totalSellPrice: Double,
    val totalBuyPrice: Double,
    val totalShipmentPrice: Double,
    val description: String,
    val date : String,
    val riyalSaudiPrice : Double,
    @TypeConverters(BookTypeConverter::class)
    @Serializable// Use the Type Converter
    val products:List<Product>

)