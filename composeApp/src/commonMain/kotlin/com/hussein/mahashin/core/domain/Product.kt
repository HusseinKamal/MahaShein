package com.hussein.mahashin.core.domain

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import kotlin.math.pow
import kotlin.random.Random

@Serializable
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val priceBuy: String="" ,
    val priceSell: String="",
    val username:String="",
    val prepayment:String="",
    )
{
    companion object {
        fun generatePositiveRandomNumber(length: Int=6): Int {
            require(length > 0) { "Length must be positive" }
            val min = 10.0.pow(length.toDouble() - 1).toInt()
            val max = 10.0.pow(length.toDouble()).toInt() - 1
            return Random.nextInt(min, max + 1)

        }
    }
}