package com.hussein.mahashin.core.data.util

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

val ColorPrimary= Color(0xFF9C27B0)
val ColorBlack= Color(0xFF000000)
val ColorWhite = Color(0xFFFFFFFF)
val ColorYellow = Color(0xFFFFEB3B)
val OFFWhite = Color(0xFFF8F8F8)
val GrayColor = Color(0xFFCFD8DC)
fun generateRandomColor(): Color {
    val random = Random
    return Color(
        red = random.nextInt(256),
        green = random.nextInt(256),
        blue = random.nextInt(256)
    )
}

val colorPalette = listOf(
    Color(0xFFBBDEFB), // Light Blue
    Color(0xFFB3E5FC), // Light Cyan
    Color(0xFFC8E6C9), // Light Green
    Color(0xFFDCEDC8), // Light Lime
    Color(0xFFFFF9C4), // Light Yellow
    Color(0xFFFFE0B2), // Light Orange
    Color(0xFFF8BBD0), // Light Pink
    Color(0xFFE1BEE7), // Light Purple
    //Color(0xFFD7CCC8), // Light Brown
    Color(0xFFCFD8DC), // Light Grey
    Color(0xFFEEEEEE), // Extra Light Grey
   // Color(0xFFF0F4C3), // Light Lime Yellow
    Color(0xFFDCE775), // Light Chartreuse
    Color(0xFFCDDC39), // Light Mustard Yellow
    Color(0xFFFFCCBC), // Light Salmon
    Color(0xFFD1C4E9), // Light Lavender
    Color(0xFFB2DFDB), // Light Teal
)
/*
val colorPalette = listOf(
    Color(0xFFF8F8F8), // Off-White
    Color(0xFFFFFDE7), // Pale Yellow
    Color(0xFF00897B), // Dark Teal/Blue
    Color(0xFFF06292), // Coral/Pink
    Color(0xFF424242)  // Dark Gray/Charcoal
)*/
