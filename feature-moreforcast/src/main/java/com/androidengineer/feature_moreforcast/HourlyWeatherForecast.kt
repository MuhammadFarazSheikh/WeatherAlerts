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
import com.androidengineer.core.domain.model.Hour
import com.androidengineer.core.ui.WeatherDetail
import com.androidengineer.core.ui.theme.rememberAppThemeValues

@Composable
fun HourlyWeatherForecast(hour: Hour) {

    val themeValues = rememberAppThemeValues()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .padding(
                themeValues.dimens.padding0,
                themeValues.dimens.padding0,
                themeValues.dimens.padding15,
                themeValues.dimens.padding0
            )
            .width(280.dp)
            .wrapContentHeight(), content = {

            WeatherDetail(
                icon = R.drawable.clock, title = "Time", value = hour.time.split(" ").last()
            )

            WeatherDetail(
                icon = R.drawable.feels_like, title = "Temperature", value = hour.tempCentigrade
            )

            WeatherDetail(
                icon = R.drawable.humidity, title = "Humidity", value = hour.humidityPercent
            )

            WeatherDetail(
                icon = R.drawable.feels_like, title = "Feels", value = hour.feelsLikeCentigrade
            )
        })
}