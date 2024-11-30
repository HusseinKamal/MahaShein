package com.hussein.mahashin.presentation.home

import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hussein.mahashin.core.data.OrderDatabase
import com.hussein.mahashin.core.data.util.RequestState
import com.hussein.mahashin.core.domain.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrderViewModel(
    private val database: OrderDatabase
):ViewModel() {

    private var _orders = MutableStateFlow<RequestState<List<Order>>>(RequestState.Loading)
    val orders= _orders.asStateFlow()

   init {
        viewModelScope.launch {
            getAllOrders()
        }
    }
    private fun getAllOrders(){
        viewModelScope.launch {
            database.orderDao().getAllOrders()
                .collect {
                    _orders.value = RequestState.Success(it)
                }
        }
    }
    fun deleteOrder(orderId: Int,
                            onSuccess: () -> Unit,
                            onError: (String) -> Unit){
        try {
            viewModelScope.launch {
                database.orderDao().deleteOrderById(orderId)
                getAllOrders()
                onSuccess()
            }
        }
        catch (e: Exception) {
            onError(e.toString())
        }
    }
}