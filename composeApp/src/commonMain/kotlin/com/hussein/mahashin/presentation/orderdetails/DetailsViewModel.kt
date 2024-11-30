package com.hussein.mahashin.presentation.orderdetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hussein.mahashin.core.data.OrderDatabase
import com.hussein.mahashin.core.domain.Order
import com.hussein.mahashin.core.domain.Product
import com.hussein.mahashin.presentation.navigation.ORDER_ID_ARG
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val database: OrderDatabase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val selectOrderId: Int = savedStateHandle.get<Int>(ORDER_ID_ARG) ?: -1

    var nameField = mutableStateOf("")
    var quantityField = mutableStateOf("")
    var totalBuyPriceField = mutableStateOf("")
    var totalSellPriceField = mutableStateOf("")
    var totalShipmentFeeField = mutableStateOf("")
    var descriptionField = mutableStateOf("")
    var dateField = mutableStateOf("")
    var riyalField = mutableStateOf("")
    var productsField = mutableStateOf(emptyList<Product>())

    var _productField = MutableStateFlow(Product())
    val productField: StateFlow<Product> = _productField.asStateFlow()

    init {
        viewModelScope.launch {
            if (selectOrderId != -1) {
                val selectedOrder = database.orderDao()
                    .getOrderById(selectOrderId)
                nameField.value = selectedOrder.name
                quantityField.value = selectedOrder.quantity.toString()
                totalBuyPriceField.value = selectedOrder.totalBuyPrice.toString()
                totalSellPriceField.value = selectedOrder.products.fold(0.0) { acc, product -> acc + product.priceSell.toDouble()}.toString()
                totalShipmentFeeField.value = selectedOrder.totalShipmentPrice.toString()
                descriptionField.value = selectedOrder.description
                dateField.value = selectedOrder.date
                riyalField.value = selectedOrder.riyalSaudiPrice.toString()
                productsField.value = selectedOrder.products
            }
        }
    }

    fun insertOrder(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                if (
                    nameField.value.isNotEmpty() &&
                    quantityField.value.isNotEmpty() &&
                    totalBuyPriceField.value.isNotEmpty() &&
                    totalSellPriceField.value.isNotEmpty() &&
                    descriptionField.value.isNotEmpty() &&
                    dateField.value.isNotEmpty()
                ) {
                    database.orderDao()
                        .insertOrder(
                            order = Order(
                                name = nameField.value,
                                quantity = quantityField.value.toInt(),
                                totalBuyPrice = totalBuyPriceField.value.toDouble(),
                                totalSellPrice = totalSellPriceField.value.toDouble(),
                                totalShipmentPrice = totalShipmentFeeField.value.toDouble(),
                                description = descriptionField.value,
                                date = dateField.value,
                                riyalSaudiPrice = riyalField.value.toDouble(),
                                products = productsField.value,
                            ),
                        )
                    onSuccess()
                } else {
                    onError("Fields cannot be empty.")
                }
            } catch (e: Exception) {
                onError(e.toString())
            }
        }
    }

    fun updateOrder(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                if (nameField.value.isNotEmpty() &&
                    quantityField.value.isNotEmpty() &&
                    totalBuyPriceField.value.isNotEmpty() &&
                    totalSellPriceField.value.isNotEmpty() &&
                    descriptionField.value.isNotEmpty() &&
                    dateField.value.isNotEmpty()
                ) {
                    database.orderDao()
                        .updateOrder(
                            order = Order(
                                id = selectOrderId,
                                name = nameField.value,
                                description = descriptionField.value,
                                totalBuyPrice = totalBuyPriceField.value.toDouble(),
                                totalSellPrice = totalSellPriceField.value.toDouble(),
                                totalShipmentPrice = totalShipmentFeeField.value.toDouble(),
                                quantity = quantityField.value.toInt(),
                                date = dateField.value,
                                riyalSaudiPrice = riyalField.value.toDouble(),
                                products = productsField.value
                            )
                        )
                    onSuccess()
                } else {
                    onError("Fields cannot be empty.")
                }
            } catch (e: Exception) {
                onError(e.toString())
            }
        }
    }
    fun deleteProduct(
        id: Int,
        productId : Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                productsField.value = productsField.value.filter { it.id != productId }
                database.orderDao()
                    .updateOrder(
                        order = Order(
                            id = selectOrderId,
                            name = nameField.value,
                            description = descriptionField.value,
                            totalBuyPrice = totalBuyPriceField.value.toDouble(),
                            totalSellPrice = totalSellPriceField.value.toDouble(),
                            totalShipmentPrice = totalShipmentFeeField.value.toDouble(),
                            quantity = quantityField.value.toInt(),
                            date = dateField.value,
                            riyalSaudiPrice = riyalField.value.toDouble(),
                            products = productsField.value
                        )
                    )
                onSuccess()
            } catch (e: Exception) {
                onError(e.toString())
            }
        }
    }
}