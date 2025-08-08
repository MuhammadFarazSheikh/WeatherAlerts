package com.androidengineer.feature

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.androidengineer.core.ui.UnderlinedTextButton
import com.androidengineer.core.ui.WeatherDetail
import com.androidengineer.core.utils.MessageDialoge
import com.androidengineer.core.utils.ShowLoaderDialogue
import com.androidengineer.core.utils.getWindDirection
import com.androidengineer.core.ui.navigateToMoreForecastScreen
import com.androidengineer.core.ui.navigateToSearchWeather
import com.androidengineer.core.ui.theme.rememberAppThemeValues
import com.androidengineer.core.utils.CheckLocationPermission
import com.androidengineer.core.utils.DATE_TIME_FORMAT_NEW
import com.androidengineer.core.utils.DATE_TIME_FORMAT_OLD
import com.androidengineer.core.utils.GetUserLocation
import com.androidengineer.core.utils.formatDateTime

@Composable
fun CurrentWeatherScreen(navHostController: NavHostController) {

    val themeValues = rememberAppThemeValues()
    val context = LocalContext.current
    val activity = context as? Activity
    val currentWeatherViewModel: CurrentWeatherViewModel = hiltViewModel()
    val state = currentWeatherViewModel.currentWeatherDataState.collectAsStateWithLifecycle().value
    val isPermissionGranted = remember { mutableStateOf(false) }

    CheckLocationPermission { permissionGranted ->
        if (permissionGranted) {
            isPermissionGranted.value = true
        }
    }

    if (isPermissionGranted.value) {
        GetUserLocation { location ->
            location?.let { loc ->
                currentWeatherViewModel.getCurrentWeatherData(loc.latitude,loc.longitude)
            }
        }
    }

    if (state.isLoading) {
        ShowLoaderDialogue()
    } else if (state.isError) {
        MessageDialoge {
            activity?.finish()
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(themeValues.colors.appBackgroundLightSkyBlue.toArgb()),
                            Color(themeValues.colors.appBackgroundPastelBlue.toArgb())
                        )
                    )
                )
                .fillMaxWidth()
                .fillMaxHeight(), content = {
                Text(
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(
                        themeValues.dimens.padding0,
                        themeValues.dimens.padding20,
                        themeValues.dimens.padding0,
                        themeValues.dimens.padding0
                    ), text = context.getString(
                        com.androidengineer.core.R.string.two_strings_concate,
                        state.location.name,
                        state.location.country
                    ), style = themeValues.typography.large, color = themeValues.colors.black
                )

                Text(
                    modifier = Modifier.padding(
                        themeValues.dimens.padding0,
                        themeValues.dimens.padding10,
                        themeValues.dimens.padding0,
                        themeValues.dimens.padding0
                    ),
                    text = formatDateTime(
                        state.location.localtime,
                        DATE_TIME_FORMAT_OLD,
                        DATE_TIME_FORMAT_NEW
                    ),
                    style = themeValues.typography.medium,
                    color = themeValues.colors.black
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                        .padding(
                            themeValues.dimens.padding0,
                            themeValues.dimens.padding20,
                            themeValues.dimens.padding0,
                            themeValues.dimens.padding0
                        )
                        .wrapContentWidth()
                        .wrapContentHeight(), content = {
                        Image(
                            modifier = Modifier.size(70.dp),
                            painter = rememberAsyncImagePainter(state.current.condition.fullIconUrl),
                            contentDescription = "Weather icon"
                        )

                        Text(
                            modifier = Modifier
                                .padding(
                                    themeValues.dimens.padding20,
                                    themeValues.dimens.padding10,
                                    themeValues.dimens.padding10,
                                    themeValues.dimens.padding10
                                )
                                .wrapContentWidth()
                                .wrapContentHeight(),
                            text = state.current.tempCentigrade,
                            style = themeValues.typography.extraLarge,
                            color = themeValues.colors.black
                        )
                    })

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                        .padding(
                            themeValues.dimens.padding40,
                            themeValues.dimens.padding0,
                            themeValues.dimens.padding40,
                            themeValues.dimens.padding0
                        )
                        .fillMaxWidth()
                        .wrapContentHeight(), content = {

                        WeatherDetail(
                            icon = R.drawable.humidity,
                            title = "Humidity",
                            value = state.current.humidityPercent
                        )

                        WeatherDetail(
                            icon = R.drawable.wind,
                            title = "Wind",
                            value = state.current.windSpeedKMPH
                        )

                        WeatherDetail(
                            icon = R.drawable.wind_direction,
                            title = "Direction",
                            value = getWindDirection(state.current.wind_dir)
                        )

                        WeatherDetail(
                            icon = R.drawable.feels_like,
                            title = "Feels",
                            value = state.current.feelsLikeCentigrade
                        )

                    })

                UnderlinedTextButton(
                    "3 Days Weather Forcast",
                    {
                        navHostController.navigateToMoreForecastScreen(state?.forecast!!)
                    },
                )

                UnderlinedTextButton(
                    "Search Weather",
                    {
                        navHostController.navigateToSearchWeather()
                    },
                )
            })
    }
}