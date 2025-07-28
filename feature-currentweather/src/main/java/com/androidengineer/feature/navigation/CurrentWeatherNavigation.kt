package com.androidengineer.feature.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.androidengineer.core.ui.CURRENT_WEATHER_NAVIGATION_ROUTE
import com.androidengineer.feature.CurrentWeatherScreen

fun NavGraphBuilder.currentWeatherScreen(navHostController: NavHostController) {
    composable(CURRENT_WEATHER_NAVIGATION_ROUTE) {
        CurrentWeatherScreen(navHostController)
    }
}