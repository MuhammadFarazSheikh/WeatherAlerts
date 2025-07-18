package com.androidengineer.feature_moreforcast

import com.androidengineer.core.CURRENT_WEATHER_NAVIGATION_ROUTE
import com.androidengineer.core.MORE_FORECAST_NAVIGATION_ROUTE

sealed class TabItem(val route: String) {
    object CurrentWeather : TabItem(CURRENT_WEATHER_NAVIGATION_ROUTE)
    object MoreForecast : TabItem(MORE_FORECAST_NAVIGATION_ROUTE)

    companion object {
        val all = listOf(CurrentWeather, MoreForecast)
    }
}