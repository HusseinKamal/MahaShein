package com.hussein.mahashin.presentation.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.hussein.mahashin.core.data.util.ColorBlack
import mahashin.composeapp.generated.resources.Res
import mahashin.composeapp.generated.resources.error_no_orders
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorView(message: String? = null) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message?:stringResource(Res.string.error_no_orders), color = ColorBlack, fontSize = 14.sp)
    }
}