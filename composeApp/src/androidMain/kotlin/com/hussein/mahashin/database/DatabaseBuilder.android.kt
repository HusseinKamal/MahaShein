package com.hussein.mahashin.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hussein.mahashin.core.data.OrderDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<OrderDatabase> {
    val dbFile = context.getDatabasePath("order.db")
    return Room.databaseBuilder(
        context = context,
        name = dbFile.absolutePath
    )
}
