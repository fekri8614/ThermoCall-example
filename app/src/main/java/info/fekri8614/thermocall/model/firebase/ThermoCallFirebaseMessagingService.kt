package info.fekri8614.thermocall.model.firebase

import android.app.Notification
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import info.fekri8614.thermocall.util.NOTIFICATION_CHANNEL_ID

private const val TAG = "ThermocallFirebaseMessagingService"

class ThermoCallFirebaseMessagingService() : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        createNotification(con = baseContext, message = message)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    private fun createNotification(con: Context, message: RemoteMessage): Notification {

        val notification = NotificationCompat.Builder(con, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.stat_notify_error)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    resources,
                    android.R.drawable.stat_notify_error
                )
            )
            .setContentTitle("Thermocall")
            .setContentText(message.data.toString())
            .build()

        return notification
    }
}