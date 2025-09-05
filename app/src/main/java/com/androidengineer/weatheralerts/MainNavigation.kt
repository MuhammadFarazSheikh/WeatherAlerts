package com.androidengineer.weatheralerts

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.androidengineer.core.ui.CURRENT_WEATHER_NAVIGATION_ROUTE
import com.androidengineer.feature.navigation.currentWeatherScreen
import com.androidengineer.feature_moreforcast.navigation.moreForecastScreen
import com.androidengineer.feature_searchweather.searchWeatherScreen

@Composable
fun MainNavigation(
    navHostController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navHostController,
        startDestination = CURRENT_WEATHER_NAVIGATION_ROUTE,
        builder = {
            currentWeatherScreen(navHostController)
            moreForecastScreen()
            searchWeatherScreen(navHostController)
        })
}