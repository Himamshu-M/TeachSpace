package com.example.notificationbaisc.screen


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notificationbaisc.uix.MainViewModel
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import com.example.notificationbaisc.navigation.Screen
@SuppressLint("MissingPermission")
@Composable
fun MainScreen(navController: NavHostController,
               mainViewModel: MainViewModel = hiltViewModel()) {
    // Example of requesting permission before showing the notification


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick =
            { mainViewModel.showSimpleNotification() }
        ) {
            Text(text = "Simple Notification")

        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick =
            mainViewModel::updateSimpleNotification
        ) {
            Text(text = "Update Notification")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = mainViewModel::cancelSimpleNotification) {
            Text(text = "Cancel Notification")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            navController.navigate(
                Screen.Details.passArgument(
                    message = "Coming from Main Screen."
                )
            )
        }) {
            Text(text = "DETAILS SCREEN")
        }
    }
}