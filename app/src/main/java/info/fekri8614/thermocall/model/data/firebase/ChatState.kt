package info.fekri8614.thermocall.model.data.firebase

data class ChatState(
    val isEnteringToken: Boolean = true,
    val remoteToken: String = "",
    val messageText: String = "",
)
