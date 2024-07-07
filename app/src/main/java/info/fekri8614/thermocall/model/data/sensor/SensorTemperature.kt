package info.fekri8614.thermocall.model.data.sensor


import com.google.gson.annotations.SerializedName

class SensorTemperatureList : ArrayList<SensorTemperature>()

data class SensorTemperature(
    @SerializedName("temperature")
    val temperature: Int,
    @SerializedName("timestamp")
    val timestamp: String
)