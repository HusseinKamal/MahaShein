package com.hussein.mahashin.presentation.navigation

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import com.hussein.mahashin.core.domain.Product
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val ProductType = object : NavType<Product>(isNullableAllowed = true) {
        override val name: String
            get() = PRODUCT_ARG


        override fun get(bundle: Bundle, key: String): Product? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Product {
            return Json.decodeFromString(value)
        }


        override fun serializeAsValue(value: Product): String {
            return Json.encodeToString(value)
        }


        override fun put(bundle: Bundle, key: String, value: Product) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}