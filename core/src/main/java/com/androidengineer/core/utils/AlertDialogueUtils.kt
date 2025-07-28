package com.androidengineer.core.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowLoaderDialogue() {
    BasicAlertDialog(
        onDismissRequest = {},
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(5.dp))
            .wrapContentWidth()
            .wrapContentHeight(),
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(0.dp, 10.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                content = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        color = Color.Black,
                        strokeWidth = 2.dp
                    )

                    Text(
                        modifier = Modifier
                            .padding(0.dp, 10.dp, 0.dp, 0.dp)
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        text = "Loading",
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                })
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageDialoge(dismiss: () -> Unit) {
    BasicAlertDialog(
        onDismissRequest = {},
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.White, shape = RoundedCornerShape(5.dp))
            .wrapContentWidth()
            .wrapContentHeight(),
        content = {
            Column(
                modifier = Modifier
                    .padding(0.dp, 10.dp, 0.dp, 10.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Text(
                        text = "Something went wrong,\nPlease try again later",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    TextButton(
                        modifier = Modifier
                            .padding(0.dp, 10.dp, 0.dp, 0.dp)
                            .wrapContentWidth()
                            .wrapContentHeight(), onClick = {
                        dismiss.invoke()
                    }, content = {
                        Text(
                            text = "Dismiss", color = Color.Black, fontSize = 15.sp
                        )
                    })
                })
        })
}