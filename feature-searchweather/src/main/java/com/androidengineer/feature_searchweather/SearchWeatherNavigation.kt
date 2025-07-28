package com.androidengineer.feature_searchweather

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.androidengineer.core.ui.SEARCH_WEATHER_NAVIGATION_ROUTE

fun NavGraphBuilder.searchWeatherScreen(navHostController: NavHostController) {
    composable(SEARCH_WEATHER_NAVIGATION_ROUTE) {
        SearchWeatherScreen(navHostController)
    }
}