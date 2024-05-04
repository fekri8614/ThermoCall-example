package info.fekri8614.thermocall.model.data.sensor


import com.google.gson.annotations.SerializedName

data class SensorUpdated(
    @SerializedName("label")
    val label: String?,
    @SerializedName("max")
    val max: Int?,
    @SerializedName("min")
    val min: Int?
)