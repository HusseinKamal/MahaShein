package com.hussein.mahashin.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.hussein.mahashin.core.data.OrderDatabase
import com.sun.tools.javac.util.Context
import java.io.File

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<OrderDatabase> {
    val os = System.getProperty("os.name").lowercase()
    val userHome = System.getProperty("user.home")
    val appDataDir = when {
        os.contains("win") -> File(System.getenv("APPDATA"), "Bookpedia")
        os.contains("mac") -> File(userHome, "Library/Application Support/Bookpedia")
        else -> File(userHome, ".local/share/Bookpedia")
    }

    if(!appDataDir.exists()) {
        appDataDir.mkdirs()
    }

    val dbFile = File(appDataDir, "order.db")
    return Room.databaseBuilder(dbFile.absolutePath)
}