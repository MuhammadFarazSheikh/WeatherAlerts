package com.androidengineer.feature

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidengineer.core.ui.WeatherForecast
import com.androidengineer.core.utils.MessageDialoge
import com.androidengineer.core.utils.ShowLoaderDialogue
import com.androidengineer.core.ui.navigateToMoreForecastScreen
import com.androidengineer.core.ui.navigateToSearchWeather
import com.androidengineer.core.ui.theme.rememberAppThemeValues
import com.androidengineer.core.utils.CheckLocationPermission
import com.androidengineer.core.utils.GetUserLocation

@Composable
fun CurrentWeatherScreen(navHostController: NavHostController) {

    val themeValues = rememberAppThemeValues()
    val activity = LocalContext.current as? Activity
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
                currentWeatherViewModel.getCurrentWeatherData(loc.latitude, loc.longitude)
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
            modifier = Modifier
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

                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                            navHostController.navigateToSearchWeather()
                        })
                        .padding(
                            themeValues.dimens.padding40,
                            themeValues.dimens.padding15,
                            themeValues.dimens.padding40,
                            themeValues.dimens.padding0
                        )
                        .background(
                            color = colorResource(com.androidengineer.core.R.color.light_white),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(themeValues.dimens.padding10)
                        .fillMaxWidth()
                        .wrapContentHeight(), content = {

                        Image(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(R.drawable.search_weather),
                            contentDescription = ""
                        )

                        Text(
                            modifier = Modifier
                                .padding(
                                    themeValues.dimens.padding10,
                                    themeValues.dimens.padding0,
                                    themeValues.dimens.padding0,
                                    themeValues.dimens.padding0
                                )
                                .wrapContentWidth()
                                .wrapContentHeight(),
                            text = "Search Weather...",
                            style = themeValues.typography.small,
                            color = themeValues.colors.black
                        )
                    })

                WeatherForecast(
                    state
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                navHostController.navigateToMoreForecastScreen(state?.forecast!!)
                            })
                        .padding(
                            themeValues.dimens.padding40,
                            themeValues.dimens.padding15,
                            themeValues.dimens.padding40,
                            themeValues.dimens.padding0
                        )
                        .background(
                            color = colorResource(com.androidengineer.core.R.color.light_white),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(themeValues.dimens.padding10)
                        .fillMaxWidth()
                        .wrapContentHeight(), content = {

                        Text(
                            modifier = Modifier
                                .padding(
                                    themeValues.dimens.padding10,
                                    themeValues.dimens.padding0,
                                    themeValues.dimens.padding0,
                                    themeValues.dimens.padding0
                                )
                                .wrapContentWidth()
                                .wrapContentHeight(),
                            text = "3 Days Weather Forecast",
                            style = themeValues.typography.small,
                            color = themeValues.colors.black
                        )

                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                        )

                        Image(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(R.drawable.arrow_right),
                            contentDescription = ""
                        )
                    })
            })
    }
}