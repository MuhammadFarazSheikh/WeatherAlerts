package com.androidengineer.weatheralerts

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.WindowInsetsControllerCompat
import com.androidengineer.core.ui.theme.LocalMyColors
import com.androidengineer.core.ui.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            ComposeView(this).apply {
                setContent {
                    val window = (context as Activity).window
                    MyAppTheme {
                        val color = LocalMyColors.current
                        SideEffect {
                            window.statusBarColor = Color(color.appBackgroundLightSkyBlue.toArgb()).toArgb()
                            WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
                        }
                        MainNavigation()
                    }
                }
            })

    }
}