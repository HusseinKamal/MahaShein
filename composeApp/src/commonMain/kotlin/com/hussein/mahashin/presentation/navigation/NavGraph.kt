package com.hussein.mahashin.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.hussein.mahashin.core.domain.Product
import com.hussein.mahashin.presentation.home.OrderScreen
import com.hussein.mahashin.presentation.orderdetails.OrderDetailsScreen
import com.hussein.mahashin.presentation.products.ProductScreen
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            OrderScreen(
                onOrderSelect = {
                    navController.navigate(Screen.Details.passId(it))
                },
                onCreateClick = {
                    navController.navigate(Screen.Details.passId(it))
                } ,
                onDeleteOrder ={
                    navController.navigate(Screen.Home.route)
                }
            )
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(ORDER_ID_ARG) { type = NavType.IntType; defaultValue = 0 }
            )
        ) {
            val orderId = it.arguments?.getInt(ORDER_ID_ARG) ?: 0
            OrderDetailsScreen(
                onOrderProductSelect = { product ->  // Pass the Product object
                    navController.navigate(Screen.Product.passId(orderId,product))
                },
                onBackClick = { navController.navigate(Screen.Home.route) },
                onOrderEditProductSelect = { orderId, product ->
                    navController.navigate(Screen.Product.passId(orderId,product))
                },
                id = orderId
            )
        }
        composable(
            route = Screen.Product.route,
            arguments = listOf(
                navArgument(ORDER_ID_ARG) {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument(PRODUCT_ARG) {
                    type = CustomNavType.ProductType
                    nullable = true // Allow nulls in case no product is passed

                }
            )

        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getInt(ORDER_ID_ARG) ?: 0
            val productJsonString = backStackEntry.arguments?.getString(PRODUCT_ARG)  // Get the JSON string
            val product: Product? = productJsonString?.let {
                try{ Json.decodeFromString<Product>(it) } catch(e: Exception){ null }
            } // Deserialize the JSON string to a Product object

            ProductScreen(
                onBackClick = { navController.navigate(Screen.Details.passId(orderId)) },
                id = orderId,
                product = product // Pass the product to ProductScreen
            )


        }
    }
}
