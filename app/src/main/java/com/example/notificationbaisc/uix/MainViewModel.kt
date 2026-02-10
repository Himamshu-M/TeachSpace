package com.example.notificationbaisc.uix

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class MainViewModel @Inject constructor(
    private val notificationBuilder: NotificationCompat.Builder,
    private val notificationManager: NotificationManagerCompat
) : ViewModel() {


    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun showSimpleNotification() {
        println ("hi")
        notificationManager.notify(1, notificationBuilder
            .build())
    }
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun updateSimpleNotification() {
        notificationManager.notify(1, notificationBuilder
            .setContentTitle("NEW TITLE")
            .build()
        )
    }

    fun cancelSimpleNotification() {
        notificationManager.cancel(1)
    }

}