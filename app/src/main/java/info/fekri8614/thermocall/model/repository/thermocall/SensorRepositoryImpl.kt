package info.fekri8614.thermocall.model.repository.thermocall

import info.fekri8614.thermocall.model.data.ThermoCall
import info.fekri8614.thermocall.model.data.firebase.SendMessageDto
import info.fekri8614.thermocall.model.data.sensor.Sensor
import info.fekri8614.thermocall.model.net.ApiService

class SensorRepositoryImpl(
    private val apiService: ApiService
) : SensorRepository {
    override suspend fun getAllThermoCalls(): List<ThermoCall> {
        return apiService.getAllSensors()
    }

    override suspend fun sendMessage(body: SendMessageDto) {
        return apiService.sendMessage(body)
    }

    override suspend fun broadcast(body: SendMessageDto) {
        return apiService.broadcast(body)
    }

    override suspend fun createSensor(body: Sensor) {
        apiService.createSensor(
            userId = body.userId,
            label = body.label,
            min = body.min,
            max = body.max
        )
    }
}