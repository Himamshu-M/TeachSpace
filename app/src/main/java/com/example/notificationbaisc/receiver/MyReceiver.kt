package com.example.notificationbaisc.receiver


import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import com.example.notificationbaisc.di.RESULT_KEY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.widget.Toast

@AndroidEntryPoint
class MyReceiver : BroadcastReceiver() {

   override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("MESSAGE")
        if (message != null) {
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
