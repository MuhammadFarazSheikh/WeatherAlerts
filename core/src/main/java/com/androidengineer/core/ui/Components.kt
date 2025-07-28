package com.androidengineer.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.androidengineer.core.R
import com.androidengineer.core.ui.theme.models.AppThemeValues
import com.androidengineer.core.ui.theme.rememberAppThemeValues

@Composable
fun WeatherDetail(
    icon: Int, title: String, value: String, themeValues: AppThemeValues = rememberAppThemeValues()
) = Row(
    verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .padding(
            themeValues.dimens.padding0,
            themeValues.dimens.padding10,
            themeValues.dimens.padding0,
            themeValues.dimens.padding0
        )
        .background(
            color = colorResource(R.color.light_white), shape = RoundedCornerShape(10.dp)
        )
        .padding(themeValues.dimens.padding10)
        .fillMaxWidth()
        .wrapContentHeight(), content = {

        Image(
            modifier = Modifier.size(30.dp),
            painter = painterResource(icon),
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
            text = title,
            style = themeValues.typography.small,
            color = themeValues.colors.black
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            text = value,
            style = themeValues.typography.small,
            color = themeValues.colors.black
        )
    })

@Composable
fun UnderlinedTextButton(
    text: String,
    onClick: () -> Unit,
    themeValues: AppThemeValues = rememberAppThemeValues(),
) = TextButton(
    modifier = Modifier
        .padding(
            themeValues.dimens.padding0,
            themeValues.dimens.padding20,
            themeValues.dimens.padding0,
            themeValues.dimens.padding0
        )
        .wrapContentWidth()
        .wrapContentHeight(), onClick = {
            onClick.invoke()
    }, content = {
        Text(
            text = text, style = TextStyle(
                color = themeValues.colors.black,
                fontSize = themeValues.typography.small.fontSize,
                textDecoration = TextDecoration.Underline
            )
        )
    })