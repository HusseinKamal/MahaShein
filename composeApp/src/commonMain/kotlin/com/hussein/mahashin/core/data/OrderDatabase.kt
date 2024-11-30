package com.hussein.mahashin.core.data

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.hussein.mahashin.core.data.dao.OrderDto
import com.hussein.mahashin.core.domain.Order


@Database(entities = [Order::class], version = 1, exportSchema = false)

@ConstructedBy(BookDatabaseConstructor::class)
@TypeConverters(BookTypeConverter::class)
abstract  class OrderDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDto
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConstructor : RoomDatabaseConstructor<OrderDatabase>{
    override fun initialize(): OrderDatabase
}