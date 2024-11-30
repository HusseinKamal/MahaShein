package com.hussein.mahashin.core.data

import androidx.room.TypeConverter
import com.hussein.mahashin.core.domain.Product
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class BookTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return Json.decodeFromString(
            ListSerializer(String.serializer()), value
        )
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Json.encodeToString(
            ListSerializer(String.serializer()), list
        )
    }

    @TypeConverter
    fun fromProductList(productList: List<Product>?): String? {
        return productList?.let { Json.encodeToString(it) }
    }

    @TypeConverter
    fun toProductList(productListString: String?): List<Product>? {
        return productListString?.let { Json.decodeFromString<List<Product>>(it) }
    }
}