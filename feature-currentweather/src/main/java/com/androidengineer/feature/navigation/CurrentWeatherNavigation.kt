package com.androidengineer.feature.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.androidengineer.feature.CurrentWeatherScreen

const val CURRENT_WEATHER_NAVIGATION_ROUTE = "CurrentWeather"

fun NavHostController.navigateToCurrentWeatherScreen(
    navOptions: NavOptions = NavOptions.Builder().build()
) = navigate("CurrentWeather",navOptions)

fun NavGraphBuilder.currentWeatherScreen() {
    composable(CURRENT_WEATHER_NAVIGATION_ROUTE) {
        CurrentWeatherScreen()
    }
}