package com.hussein.mahashin.presentation.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hussein.mahashin.core.data.util.ColorPrimary

@Composable
fun Tag(text: String, backgroundColor: Color = Color.LightGray, borderColor: Color = Color.Gray) {
    Text(
        text = text,
        fontSize = 12.sp,
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp) // Adjust corner radius as needed
            )
            .border(BorderStroke(1.dp, borderColor), shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        color = ColorPrimary // Text color based on Material theme
    )
}