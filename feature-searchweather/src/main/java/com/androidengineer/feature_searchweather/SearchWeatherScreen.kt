package com.androidengineer.feature_searchweather

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.androidengineer.core.ui.WeatherForecast
import com.androidengineer.core.ui.theme.rememberAppThemeValues
import com.androidengineer.core.utils.MessageDialoge
import com.androidengineer.core.utils.ShowLoaderDialogue

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
                WeatherForecast(state)
            }
        })

}