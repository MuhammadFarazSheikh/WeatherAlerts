package com.androidengineer.core.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class MyColors(
    val appBackgroundLightSkyBlue: Color,
    val appBackgroundPastelBlue: Color,
    val black: Color,
    val semiTransparentWhite: Color
)

data class MyDimens(
    val padding0: Dp,
    val padding10: Dp,
    val padding15: Dp,
    val padding20: Dp,
    val padding40: Dp,
    val padding50: Dp
)

data class MyTypography(
    val small: TextStyle, val medium: TextStyle, val large: TextStyle, val extraLarge: TextStyle
)