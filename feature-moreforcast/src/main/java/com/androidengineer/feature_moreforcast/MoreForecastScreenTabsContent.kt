package com.androidengineer.feature_moreforcast

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.androidengineer.core.domain.Forecastday

@Composable
fun MoreforecastScreenTabsContent(forecast: Forecastday) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFB3E5FC), Color(0xFFE1F5FE))
                )
            )
            .fillMaxWidth()
            .fillMaxHeight(), content = {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                    .wrapContentWidth()
                    .wrapContentHeight(),
                content = {
                    Image(
                        modifier = Modifier.size(70.dp),
                        painter = rememberAsyncImagePainter(forecast.day.condition.fullIconUrl),
                        contentDescription = "Weather icon"
                    )

                    Text(
                        modifier = Modifier
                            .padding(20.dp, 10.dp, 10.dp, 10.dp)
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        text = forecast.day.tempCentigrade,
                        fontSize = 60.sp,
                        color = Color.Black
                    )
                })

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(
                                color = colorResource(R.color.light_white),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(10.dp)
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        content = {

                            Image(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(R.drawable.humidity),
                                contentDescription = "Humidity icon"
                            )

                            Text(
                                modifier = Modifier
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                                    .wrapContentWidth()
                                    .wrapContentHeight(),
                                text = "Humidity",
                                fontSize = 15.sp,
                                color = Color.Black
                            )

                            Text(
                                modifier = Modifier
                                    .padding(20.dp, 0.dp, 0.dp, 0.dp)
                                    .wrapContentWidth()
                                    .wrapContentHeight(),
                                text = forecast.day.humidityPercent,
                                fontSize = 15.sp,
                                color = Color.Black
                            )
                        })

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(
                                color = colorResource(R.color.light_white),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(10.dp)
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        content = {

                            Image(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(R.drawable.wind),
                                contentDescription = "Wind icon"
                            )

                            Text(
                                modifier = Modifier
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                                    .wrapContentWidth()
                                    .wrapContentHeight(),
                                text = "Wind",
                                fontSize = 15.sp,
                                color = Color.Black
                            )

                            Text(
                                modifier = Modifier
                                    .padding(20.dp, 0.dp, 0.dp, 0.dp)
                                    .wrapContentWidth()
                                    .wrapContentHeight(),
                                text = forecast.day.windSpeedKMPH,
                                fontSize = 15.sp,
                                color = Color.Black
                            )
                        })
                })

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    .background(
                        color = colorResource(R.color.light_white),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(10.dp)
                    .wrapContentWidth()
                    .wrapContentHeight(),
                content = {

                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(R.drawable.sunrise),
                        contentDescription = "Sunrise icon"
                    )

                    Text(
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        text = "Sunrise",
                        fontSize = 15.sp,
                        color = Color.Black
                    )

                    Text(
                        modifier = Modifier
                            .padding(50.dp, 0.dp, 0.dp, 0.dp)
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        text = forecast.astro.sunrise,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                })

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    .background(
                        color = colorResource(R.color.light_white),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(10.dp)
                    .wrapContentWidth()
                    .wrapContentHeight(),
                content = {

                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(R.drawable.sunset),
                        contentDescription = "Sunset icon"
                    )

                    Text(
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        text = "Sunset",
                        fontSize = 15.sp,
                        color = Color.Black
                    )

                    Text(
                        modifier = Modifier
                            .padding(50.dp, 0.dp, 0.dp, 0.dp)
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        text = forecast.astro.sunset,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                })

            Text(
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                    .wrapContentWidth()
                    .wrapContentHeight(),
                text = "Hourly Forecast",
                fontSize = 20.sp,
                color = Color.Black
            )

            LazyRow(
                modifier = Modifier
                    .padding(20.dp, 20.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(), content = {
                    items(forecast.hour.size) { index ->
                        HourlyWeatherForecast(forecast.hour[index])
                    }
                })
        })
}