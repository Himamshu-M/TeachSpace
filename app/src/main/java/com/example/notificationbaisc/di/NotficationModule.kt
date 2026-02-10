package com.example.notificationbaisc.di

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PRIVATE
import androidx.core.app.NotificationCompat.VISIBILITY_SECRET
import androidx.core.app.NotificationManagerCompat
import com.example.notificationbaisc.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import android.content.Intent
import com.example.notificationbaisc.receiver.MyReceiver
import com.example.notificationbaisc.navigation.*
import androidx.core.net.toUri
import android.app.TaskStackBuilder
import com.example.notificationbaisc.uix.MainActivity
import android.app.RemoteInput
import android.app.Person
const val RESULT_KEY = "RESULT_KEY"
@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {
    @Singleton
    @Provides
    fun provideNotificationBuilder(
            @ApplicationContext context: Context
        ): NotificationCompat.Builder {










        val intent=Intent(context, MyReceiver::class.java).apply{
            putExtra("MESSAGE","Clicked")
        }
        val flag=
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                PendingIntent.FLAG_IMMUTABLE
        }else
            0

        val pendingIntent= PendingIntent.getBroadcast(
            context,0,intent,flag
        )

        val clickIntent = Intent(
            Intent.ACTION_VIEW,
            "$MY_URI/$MY_ARG=Coming from Notification".toUri(),
            context,
            MainActivity::class.java
        )
        val clickPendingIntent: PendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(clickIntent)
            getPendingIntent(1, flag)
        }

        return NotificationCompat.Builder(context, "Main Channel ID")
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle("Welcome")
            .setContentText("Study Channel: For every bookworm")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(VISIBILITY_PRIVATE)
            .setPublicVersion(
                NotificationCompat.Builder(context, "Main Channel ID")
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setContentTitle("Hidden")
                    .setContentText("Unlock to see the message.")
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build()
            )
            .addAction(0,"ACTION",pendingIntent)
            .setContentIntent(clickPendingIntent)
    }

    @Singleton
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManagerCompat {
        val notificationManager = NotificationManagerCompat.from(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "Main Channel ID",
                "Main Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        return notificationManager
    }

}