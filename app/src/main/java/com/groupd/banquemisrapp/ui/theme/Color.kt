package com.groupd.banquemisrapp.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Maroon = Color(0xFF631627)

val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)
val Gold = Color(0xFFAC8B09)
val Green = Color(0xFF63A85C)
val Red = Color(0xFFB11313)

val background = Brush.verticalGradient(
    listOf(
        Color.White,
        Color.Red.copy(alpha = 0.1f)
    )
)
val background2 = Brush.verticalGradient(
    listOf(

        Color.Yellow.copy(alpha = 0.1f),
        Color.White,
        Color.Red.copy(alpha = 0.1f),
        //Color.Blue.copy(alpha = 0.05f),



    )

)

val whiteBackground = Brush.verticalGradient(
    listOf(
        Color.White.copy(alpha = 0.9999f),
        White.copy(alpha = 0.1f)
    )
)