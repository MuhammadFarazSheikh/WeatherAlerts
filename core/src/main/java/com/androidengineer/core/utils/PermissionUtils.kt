package com.androidengineer.core.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices

@Composable
fun CheckLocationPermission(permissionGranted: (Boolean) -> Unit) {
    val context = LocalContext.current
    val permission = Manifest.permission.ACCESS_FINE_LOCATION

    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context, permission
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted -> hasPermission = granted })

    LaunchedEffect(Unit) {
        if (!hasPermission) {
            launcher.launch(permission)
        }
    }

    if (hasPermission) {
        permissionGranted.invoke(true)
    }
}

@Composable
@SuppressLint("MissingPermission")
fun GetUserLocation(location: (Location?) -> Unit) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val client = LocationServices.getFusedLocationProviderClient(context)
        client.lastLocation.addOnSuccessListener { loc -> location.invoke(loc) }
            .addOnFailureListener { failure ->
                location.invoke(null)
                println(failure.message)
            }
    }
}