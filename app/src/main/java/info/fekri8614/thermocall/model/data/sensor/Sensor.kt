package info.fekri8614.thermocall.model.data.sensor

import com.google.gson.annotations.SerializedName

data class Sensor(
    @SerializedName("sensorId")
    val sensorId: String,
    @SerializedName("label")
    val label: String,
    @SerializedName("min")
    val min: String,
    @SerializedName("max")
    val max: String
)
