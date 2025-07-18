package com.androidengineer.feature_moreforcast

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.androidengineer.core.domain.Hour
import com.androidengineer.core.getWindDirection

@Composable
fun HourlyWeatherForecast(hour: Hour) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(0.dp, 0.dp, 15.dp, 0.dp)
            .width(280.dp)
            .wrapContentHeight(),
        content = {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(
                        color = colorResource(R.color.light_white),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(10.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                content = {

                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(R.drawable.clock),
                        contentDescription = "Temperature icon"
                    )

                    Text(
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        text = "Time",
                        fontSize = 15.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        text = hour.time.split(" ").last(),
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
                    .fillMaxWidth()
                    .wrapContentHeight(),
                content = {

                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = rememberAsyncImagePainter(hour.condition.fullIconUrl),
                        contentDescription = "Temperature icon"
                    )

                    Text(
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        text = "Temperature",
                        fontSize = 15.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        text = hour.tempCentigrade,
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
                    .fillMaxWidth()
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

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        text = hour.humidityPercent,
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
                    .fillMaxWidth()
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

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        text = hour.feelsLikeCentigrade,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                })

        }
    )
}