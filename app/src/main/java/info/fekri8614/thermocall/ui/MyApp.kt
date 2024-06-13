package info.fekri8614.thermocall.ui

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import info.fekri8614.thermocall.di.myModule
import info.fekri8614.thermocall.util.NOTIFICATION_CHANNEL_ID
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, "TestChannel", NotificationManager.IMPORTANCE_HIGH)
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            notificationChannel.description = "Thermocall, realtime controlling and monitoring system for lab-freezers."
            notificationManager.createNotificationChannel(notificationChannel)
        }

    }

}