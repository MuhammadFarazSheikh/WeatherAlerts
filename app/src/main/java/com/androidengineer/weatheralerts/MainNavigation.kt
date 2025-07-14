package com.androidengineer.weatheralerts

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.androidengineer.feature.navigation.CURRENT_WEATHER_NAVIGATION_ROUTE
import com.androidengineer.feature.navigation.currentWeatherScreen

@Composable
fun MainNavigation(
    navHostController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navHostController, startDestination = CURRENT_WEATHER_NAVIGATION_ROUTE, builder = {
            currentWeatherScreen()
        })
}