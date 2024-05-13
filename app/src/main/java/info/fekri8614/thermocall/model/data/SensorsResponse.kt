package info.fekri8614.thermocall.model.data


import com.google.gson.annotations.SerializedName
import info.fekri8614.thermocall.model.data.sensor.SensorTemperature

class SensorsResponse : ArrayList<ThermoCall>()

data class ThermoCall(
    @SerializedName("id")
    val id: String,
    @SerializedName("sensorId")
    val sensorId: String,
    @SerializedName("label")
    val label: String,
    @SerializedName("min")
    val min: Int,
    @SerializedName("max")
    val max: Int,
    @SerializedName("currentTemperature")
    val currentTemperature: SensorTemperature
)
