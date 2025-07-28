package com.androidengineer.feature_moreforcast.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.androidengineer.core.ui.MORE_FORECAST_NAVIGATION_ROUTE
import com.androidengineer.core.domain.model.Forecast
import com.androidengineer.feature_moreforcast.MoreForecastScreenTabs
import kotlinx.serialization.json.Json

fun NavGraphBuilder.moreForecastScreen() {
    composable(
        "$MORE_FORECAST_NAVIGATION_ROUTE/{forecastData}",
        arguments = listOf(navArgument("forecastData") { type = NavType.StringType })
    ) { backStackEntry ->
        val encodedJson = backStackEntry.arguments?.getString("forecastData")
        val forecastData = encodedJson?.let { json ->
            Json.decodeFromString<Forecast>(Uri.decode(json))
        }

        forecastData?.let {
            MoreForecastScreenTabs(it)
        }
    }
}