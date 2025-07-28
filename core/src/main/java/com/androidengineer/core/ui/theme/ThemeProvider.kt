package com.androidengineer.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidengineer.core.ui.theme.models.AppThemeValues

val LocalMyColors = staticCompositionLocalOf<MyColors> {
    error("No colors provided")
}
val LocalMyDimens = staticCompositionLocalOf<MyDimens> {
    error("No dimensions provided")
}
val LocalMyTypography = staticCompositionLocalOf<MyTypography> {
    error("No typography provided")
}

@Composable
fun rememberAppThemeValues(): AppThemeValues {
    return AppThemeValues(LocalMyColors.current, LocalMyDimens.current, LocalMyTypography.current)
}

@Composable
fun MyAppTheme(
    colors: MyColors = MyColors(
        appBackgroundLightSkyBlue = Color(0xFFB3E5FC),
        appBackgroundPastelBlue = Color(0xFFE1F5FE),
        black = Color(0xFF000000),
        semiTransparentWhite = Color(0x50FFFFFF),
    ), dimens: MyDimens = MyDimens(
        padding0 = 0.dp,
        padding10 = 10.dp,
        padding15 = 15.dp,
        padding20 = 20.dp,
        padding40 = 40.dp,
        padding50 = 50.dp
    ), typography: MyTypography = MyTypography(
        small = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
        ), medium = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
        ), large = TextStyle(
            fontSize = 25.sp,
            fontWeight = FontWeight.Light,
        ), extraLarge = TextStyle(
            fontSize = 60.sp,
            fontWeight = FontWeight.ExtraBold,
        )
    ), content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalMyColors provides colors,
        LocalMyDimens provides dimens,
        LocalMyTypography provides typography,
        content = content
    )
}