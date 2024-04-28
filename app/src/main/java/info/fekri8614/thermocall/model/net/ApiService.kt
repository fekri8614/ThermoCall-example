package info.fekri8614.thermocall.model.net

import info.fekri8614.thermocall.model.data.*
import info.fekri8614.thermocall.model.data.firebase.SendMessageDto
import info.fekri8614.thermocall.model.data.sensor.Sensor
import info.fekri8614.thermocall.util.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

// Write http(s) requests here
interface ApiService {
    // https://thermocall-api.rubikamp.org/api/v1/

    @GET("/my-sensors")
    suspend fun getAllSensors(
        @Header("user-id") userId: String = USER_ID
    ): SensorsResponse

    @GET("/my-sensors/{id}")
    suspend fun getSensorById(
        @Header("user-id") userId: String = USER_ID,
        @Path("id") sensorId: String
    ): Sensor

    @GET("/my-sensors/{id}/history")
    suspend fun getSensorHistory(
        @Header("user-id") userId: String = USER_ID,
        @Path("id") sensorId: String
    )

    @POST("/send")
    suspend fun sendMessage(
        @Body body: SendMessageDto
    )

    @POST("/broadcast")
    suspend fun broadcast(
        @Body body: SendMessageDto
    )

    @POST("/my-sensors")
    suspend fun createSensor(
        @Header("user-id") userId: String = USER_ID,
        @Body sensorData: Sensor
    )

    @DELETE("/my-sensor/{id}")
    suspend fun deleteSensorById(
        @Header("user-id") userId: String = USER_ID,
        @Path("id") sensorId: String
    )

}

fun createApiService(): ApiService {
    val retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    return retrofit.create(ApiService::class.java)
}