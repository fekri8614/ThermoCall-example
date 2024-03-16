package info.fekri8614.thermocall.model.firebase

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class ThermoCallFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        // Respond to received messages
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Update server
    }
}