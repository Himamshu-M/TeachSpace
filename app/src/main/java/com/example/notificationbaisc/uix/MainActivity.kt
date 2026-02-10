package com.example.notificationbaisc.uix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notificationbaisc.screen.MainScreen
import com.example.notificationbaisc.ui.theme.NOtificationbaiscTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.result.contract.ActivityResultContracts
import android.os.Build
import android.content.pm.PackageManager
import android.Manifest
import androidx.core.content.ContextCompat
import androidx.activity.viewModels
import com.example.notificationbaisc.navigation.SetupNavGraph
import androidx.navigation.compose.rememberNavController
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Optional: Logic if permission is granted immediately
            //viewModel.showSimpleNotification()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NOtificationbaiscTheme {

                val navController= rememberNavController()
                // 2. Pass a lambda to your screen to trigger the check
                SetupNavGraph(navController)
                checkAndRequestPermission()

            }
        }
    }

    private fun checkAndRequestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED) {
                //viewModel.showSimpleNotification ()// Already have permission? Call the ViewModel logic (see below)
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }else{
            //viewModel.showSimpleNotification()// :: is a reference works for onClick not for other ask not directly executing it
        }
    }
}

