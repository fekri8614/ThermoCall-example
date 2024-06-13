package info.fekri8614.thermocall.model.data.firebase

import com.google.gson.annotations.SerializedName

data class FCMRequestBody(
    @SerializedName("fcmToken")
    val fcmToken: String
)
