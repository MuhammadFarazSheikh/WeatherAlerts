package com.androidengineer.feature_searchweather

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.androidengineer.core.ui.GoogleMaps
import com.androidengineer.core.ui.WeatherDetail
import com.androidengineer.core.ui.theme.rememberAppThemeValues
import com.androidengineer.core.utils.DATE_TIME_FORMAT_NEW
import com.androidengineer.core.utils.DATE_TIME_FORMAT_OLD
import com.androidengineer.core.utils.MessageDialoge
import com.androidengineer.core.utils.ShowLoaderDialogue
import com.androidengineer.core.utils.getWindDirection
import com.androidengineer.core.utils.formatDateTime

@Composable
fun SearchWeatherScreen(navHostController: NavHostController) {

    val themeValues = rememberAppThemeValues()
    val searchWeatherViewModel: SearchWeatherViewModel = hiltViewModel()
    val state = searchWeatherViewModel.searchState.collectAsStateWithLifecycle().value

    val searchQuery = rememberSaveable { mutableStateOf("") }

    if (state.isLoading) {
        ShowLoaderDialogue()
    } else if (state.isError) {
        MessageDialoge {
            navHostController.popBackStack()
        }
    }

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
            TextField(
                modifier = Modifier
                    .padding(
                        themeValues.dimens.padding10,
                        themeValues.dimens.padding10,
                        themeValues.dimens.padding10,
                        themeValues.dimens.padding0
                    )
                    .border(
                        border = BorderStroke(1.dp, color = themeValues.colors.black),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .fillMaxWidth()
                    .wrapContentHeight(),
                value = searchQuery.value,
                onValueChange = {
                    searchQuery.value = it
                },
                placeholder = {
                    Text(
                        text = "Search Weather",
                        fontSize = 15.sp,
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    disabledTextColor = Color.Black,
                    errorIndicatorColor = Color.Transparent,
                ),
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = {
                        searchWeatherViewModel.searchWeather(searchQuery.value)
                    }, content = {

                    })
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(R.drawable.search),
                        contentDescription = "Search icon"
                    )
                })

            if (state.loaded) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally, content = {
                        Text(
                            modifier = Modifier.padding(
                                themeValues.dimens.padding0,
                                themeValues.dimens.padding20,
                                themeValues.dimens.padding0,
                                themeValues.dimens.padding0
                            ),
                            text = state.location.name + ",\n" + state.location.country,
                            style = themeValues.typography.large,
                            color = themeValues.colors.black
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
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(
                                    themeValues.dimens.padding0,
                                    themeValues.dimens.padding20,
                                    themeValues.dimens.padding0,
                                    themeValues.dimens.padding0
                                )
                                .wrapContentWidth()
                                .wrapContentHeight(),
                            content = {
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
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(
                                    themeValues.dimens.padding40,
                                    themeValues.dimens.padding0,
                                    themeValues.dimens.padding40,
                                    themeValues.dimens.padding0
                                )
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            content = {

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

                                GoogleMaps(
                                    state.location.lat,
                                    state.location.lon,
                                    state.location.name,
                                    state.location.country
                                )

                            })
                    })
            }
        })

}