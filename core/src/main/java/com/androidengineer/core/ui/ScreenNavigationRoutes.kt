package com.androidengineer.core.ui

import android.net.Uri
import androidx.navigation.NavHostController
import com.androidengineer.core.domain.model.Forecast
import kotlinx.serialization.json.Json

const val CURRENT_WEATHER_NAVIGATION_ROUTE = "CurrentWeather"
const val MORE_FORECAST_NAVIGATION_ROUTE = "MoreForecast"
const val SEARCH_WEATHER_NAVIGATION_ROUTE = "SearchWeather"

fun NavHostController.navigateToCurrentWeatherScreen(
) = navigate(CURRENT_WEATHER_NAVIGATION_ROUTE)

fun NavHostController.navigateToSearchWeather(
) = navigate(SEARCH_WEATHER_NAVIGATION_ROUTE)

fun NavHostController.navigateToMoreForecastScreen(
    forecast: Forecast
) {
    val json = Uri.encode(Json.encodeToString(forecast))
    val fullRoute = "$MORE_FORECAST_NAVIGATION_ROUTE/$json"
    navigate(fullRoute)
}
