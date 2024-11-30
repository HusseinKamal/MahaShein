package com.hussein.mahashin.presentation.products

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
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
                totalSellPriceField.value = selectedOrder.totalSellPrice.toString()
                totalShipmentFeeField.value = selectedOrder.totalShipmentPrice.toString()
                descriptionField.value = selectedOrder.description
                dateField.value = selectedOrder.date
                riyalField.value = selectedOrder.riyalSaudiPrice.toString()
                productsField.value = selectedOrder.products
            }
        }
    }
    fun initializeProduct(product: Product) {
        _productField.update {product} // Create a copy
    }

    fun onProductNameChange(name: String) {
        _productField.update { it.copy(name = name) }
    }

    fun onProductDescriptionChange(description: String) {
        _productField.update { it.copy(description = description) }
    }

    fun onProductPriceBuyChange(priceBuy: String) {
        _productField.update { it.copy(priceBuy = priceBuy) }
    }

    fun onProductPriceSellChange(priceSell: String) {
        _productField.update { it.copy(priceSell = priceSell) }
    }
    fun onProductUsernameChange(username: String) {
        _productField.update { it.copy(username = username) }
    }
    fun onProductPrepaymentChange(prepayment: String) {
        _productField.update { it.copy(prepayment = prepayment) }
    }

    fun insertProduct(
        product: Product,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val newProduct = product.copy(id = Product.generatePositiveRandomNumber())
                _productField.value = newProduct
                productsField.value += newProduct
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
    fun updateProduct(
        product: Product,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                productsField.value = productsField.value.filterNot { it.id == product.id }
                _productField.value = product
                productsField.value += product
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