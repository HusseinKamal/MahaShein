package com.hussein.mahashin.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hussein.mahashin.core.domain.Order
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDto {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order)

    @Update
    suspend fun updateOrder(order: Order)

    @Query("SELECT * FROM orders")
    fun getAllOrders(): Flow<List<Order>>

    @Query("SELECT * FROM orders WHERE id = :id")
    suspend fun getOrderById(id: Int): Order

    @Query("DELETE FROM orders WHERE id = :id")
    suspend fun deleteOrderById(id: Int)


}