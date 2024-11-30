package com.hussein.mahashin.presentation.orderdetails

import com.hussein.mahashin.core.domain.Order

sealed class MainIntent {
    data class AddOrder(val order: Order) : MainIntent()
    data class EditOrder(val order: Order) : MainIntent()
}