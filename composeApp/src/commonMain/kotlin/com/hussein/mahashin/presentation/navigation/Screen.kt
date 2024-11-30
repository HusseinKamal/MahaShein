package com.hussein.mahashin.presentation.navigation

import com.hussein.mahashin.core.domain.Product as ProductDomain
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


const val ORDER_ID_ARG = "orderId"
const val PRODUCT_ARG = "product"

sealed class Screen(val route: String) {
    data object Home: Screen(route = "home_screen")
    data object Details: Screen(route = "details_screen/{$ORDER_ID_ARG}") {
        fun passId(id: Int) = "details_screen/$id"
    }
    data object Product: Screen(route = "product_screen/{$ORDER_ID_ARG}/${PRODUCT_ARG}/{product}") {
        fun passId(orderId: Int,product: ProductDomain?): String {
            return "product_screen/$orderId/${PRODUCT_ARG}/${Json.encodeToString(product)}"
        }
    }
}