package com.androidengineer.feature

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.androidengineer.formatDate

@Composable
fun CurrentWeatherScreen() {

    val currentWeatherViewModel: CurrentWeatherViewModel = hiltViewModel()
    val state = currentWeatherViewModel.currentWeatherDataState.collectAsStateWithLifecycle().value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFB3E5FC), Color(0xFFE1F5FE))
                )
            )
            .fillMaxWidth()
            .fillMaxHeight(), content = {
            Text(
                modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp),
                text = state.location.name+", "+state.location.country,
                fontSize = 25.sp,
                color = Color.Black
            )

            Text(
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
                text = formatDate(state.location.localtime),
                fontSize = 18.sp,
                color = Color.Black
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                    .wrapContentWidth()
                    .wrapContentHeight(), content = {
                    Image(
                        modifier = Modifier.size(70.dp),
                        painter = rememberAsyncImagePainter(state.current.condition.fullIconUrl),
                        contentDescription = "Weather icon"
                    )

                    Text(
                        modifier = Modifier
                            .padding(20.dp, 10.dp, 10.dp, 10.dp)
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        text = state.current.tempCentigrade,
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
                                text = state.current.humidityPercent,
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
                                contentDescription = "Humidity icon"
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
                                text = state.current.windSpeedKMPH,
                                fontSize = 15.sp,
                                color = Color.Black
                            )
                        })
                })

            Row(
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
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
                                painter = painterResource(R.drawable.wind_direction),
                                contentDescription = "Wind direction icon"
                            )

                            Text(
                                modifier = Modifier
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                                    .wrapContentWidth()
                                    .wrapContentHeight(),
                                text = "Direction",
                                fontSize = 15.sp,
                                color = Color.Black
                            )

                            Text(
                                modifier = Modifier
                                    .padding(20.dp, 0.dp, 0.dp, 0.dp)
                                    .wrapContentWidth()
                                    .wrapContentHeight(),
                                text = state.current.wind_dir,
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
                                painter = painterResource(R.drawable.feels_like),
                                contentDescription = "Feels like icon"
                            )

                            Text(
                                modifier = Modifier
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                                    .wrapContentWidth()
                                    .wrapContentHeight(),
                                text = "Feels",
                                fontSize = 15.sp,
                                color = Color.Black
                            )

                            Text(
                                modifier = Modifier
                                    .padding(20.dp, 0.dp, 0.dp, 0.dp)
                                    .wrapContentWidth()
                                    .wrapContentHeight(),
                                text = state.current.feelsLikeCentigrade,
                                fontSize = 15.sp,
                                color = Color.Black
                            )
                        })
                })

            TextButton(
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                    .wrapContentWidth()
                    .wrapContentHeight(), onClick = {

            }, content = {
                Text(
                    text = "3 Days Weather Forcast", style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        textDecoration = TextDecoration.Underline
                    )
                )
            })
        })
}