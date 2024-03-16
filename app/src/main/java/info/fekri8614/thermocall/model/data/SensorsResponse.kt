package info.fekri8614.thermocall.model.data


import com.google.gson.annotations.SerializedName

class SensorsResponse : ArrayList<ThermoCall>()

data class ThermoCall(
    @SerializedName("id")
    val id: String,
    @SerializedName("label")
    val label: String,
    @SerializedName("max")
    val max: Int,
    @SerializedName("min")
    val min: Int,
    @SerializedName("sensorId")
    val sensorId: String
)
