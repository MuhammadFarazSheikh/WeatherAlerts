package com.androidengineer.feature_moreforcast

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.androidengineer.core.domain.Forecast
import com.androidengineer.formatDate

@Composable
fun MoreForecastScreenTabs(forecast: Forecast) {

    val navController = rememberNavController()
    val tabs = forecast.forecastday.map { it.date }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val selectedIndex = tabs.indexOf(currentRoute).takeIf { it >= 0 } ?: 0

    Scaffold(
        topBar = {
            TabRow(
                containerColor = Color(0xFFB3E5FC),
                contentColor = Color.Black,
                divider = {},
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedIndex]),
                        color = Color(0xFFE1F5FE),
                        height = 2.dp
                    )
                },
                selectedTabIndex = selectedIndex
            ) {
                tabs.forEachIndexed { index, date ->
                    Tab(
                        selected = selectedIndex == index,
                        onClick = {
                            if (currentRoute != date) {
                                navController.navigate(date) {
                                    popUpTo(tabs.first()) { inclusive = false }
                                    launchSingleTop = true
                                }
                            }
                        },
                        text = { Text(formatDate(date)) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = tabs.first(),
            modifier = Modifier.padding(innerPadding)
        ) {
            tabs.forEach { date ->
                composable(date) {
                    MoreforecastScreenTabsContent(forecast.forecastday.find { it.date == date }!!)
                }
            }
        }
    }
}