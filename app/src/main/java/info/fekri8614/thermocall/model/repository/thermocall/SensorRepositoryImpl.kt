package info.fekri8614.thermocall.model.repository.thermocall

import info.fekri8614.thermocall.model.data.ThermoCall
import info.fekri8614.thermocall.model.data.firebase.SendMessageDto
import info.fekri8614.thermocall.model.data.sensor.Sensor
import info.fekri8614.thermocall.model.data.sensor.SensorTemperature
import info.fekri8614.thermocall.model.data.sensor.SensorUpdated
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
            sensorData = Sensor(
                sensorId = body.sensorId,
                label = body.label,
                min = body.min,
                max = body.max,
                currentTemperature = body.currentTemperature
            )
        )
    }

    override suspend fun getSensorById(sensorId: String): Sensor {
        return apiService.getSensorById(sensorId = sensorId)
    }

    override suspend fun deleteSensorById(sensorId: String) {
        apiService.deleteSensorById(sensorId = sensorId)
    }

    override suspend fun getSensorHistory(sensorId: String): ArrayList<SensorTemperature> {
        return apiService.getSensorHistory(sensorId = sensorId)
    }

    override suspend fun updateSensor(sensorId: String, data: SensorUpdated) {
        apiService.updateSensor(sensorId = sensorId, sensorData = data)
    }
}