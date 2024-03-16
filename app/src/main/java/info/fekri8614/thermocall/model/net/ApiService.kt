package info.fekri8614.thermocall.model.net

import info.fekri8614.thermocall.model.data.SensorsResponse
import info.fekri8614.thermocall.model.data.firebase.SendMessageDto
import info.fekri8614.thermocall.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

// Write http(s) requests here
interface ApiService {
    // https://thermocall-api.rubikamp.org/apiuiop/v1/

    @GET("my-sensors/")
    suspend fun getAllSensors(
        @Header("user-id") userId: String = "0SGvUxSRWbXx17hLj9iWAIznLYp2"
    ): SensorsResponse

    @POST("/send")
    suspend fun sendMessage(
        @Body body: SendMessageDto
    )

    @POST("/broadcast")
    suspend fun broadcast(
        @Body body: SendMessageDto
    )
}

fun createApiService(): ApiService {
    val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    return retrofit.create(ApiService::class.java)
}