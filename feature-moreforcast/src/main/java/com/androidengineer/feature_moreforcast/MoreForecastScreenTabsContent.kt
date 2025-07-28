package com.androidengineer.feature_moreforcast

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.androidengineer.core.domain.model.Forecastday
import com.androidengineer.core.ui.WeatherDetail
import com.androidengineer.core.ui.theme.rememberAppThemeValues

@Composable
fun MoreforecastScreenTabsContent(forecast: Forecastday) {

    val themeValues = rememberAppThemeValues()

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
                        painter = rememberAsyncImagePainter(forecast.day?.condition?.fullIconUrl),
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
                        text = forecast?.day?.tempCentigrade!!,
                        style = themeValues.typography.small,
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
                        value = forecast.day?.humidityPercent!!
                    )

                    WeatherDetail(
                        icon = R.drawable.wind,
                        title = "Wind",
                        value = forecast.day?.windSpeedKMPH!!
                    )

                    WeatherDetail(
                        icon = R.drawable.sunrise,
                        title = "Sunrise",
                        value = forecast.astro?.sunrise!!
                    )

                    WeatherDetail(
                        icon = R.drawable.sunset,
                        title = "Sunset",
                        value = forecast.astro?.sunset!!
                    )
                }
            )

            Text(
                modifier = Modifier
                    .padding(
                        themeValues.dimens.padding0,
                        themeValues.dimens.padding20,
                        themeValues.dimens.padding0,
                        themeValues.dimens.padding0
                    )
                    .wrapContentWidth()
                    .wrapContentHeight(),
                text = "Hourly Forecast",
                style = themeValues.typography.small,
                color = themeValues.colors.black
            )

            LazyRow(
                modifier = Modifier
                    .padding(
                        themeValues.dimens.padding20,
                        themeValues.dimens.padding20,
                        themeValues.dimens.padding0,
                        themeValues.dimens.padding0
                    )
                    .fillMaxWidth()
                    .wrapContentHeight(), content = {
                    items(forecast.hour?.size!!) { index ->
                        HourlyWeatherForecast(forecast?.hour?.get(index)!!)
                    }
                })
        })
}