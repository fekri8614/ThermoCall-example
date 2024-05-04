package info.fekri8614.thermocall.model.repository.thermocall

import info.fekri8614.thermocall.model.data.ThermoCall
import info.fekri8614.thermocall.model.data.firebase.SendMessageDto
import info.fekri8614.thermocall.model.data.sensor.Sensor
import info.fekri8614.thermocall.model.data.sensor.SensorTemperature
import info.fekri8614.thermocall.model.data.sensor.SensorUpdated

interface SensorRepository {
    suspend fun getAllThermoCalls(): List<ThermoCall>

    suspend fun sendMessage(body: SendMessageDto)

    suspend fun broadcast(body: SendMessageDto)

    suspend fun createSensor(body: Sensor)

    suspend fun getSensorById(sensorId: String): Sensor

    suspend fun deleteSensorById(sensorId: String)

    suspend fun getSensorHistory(sensorId: String): ArrayList<SensorTemperature>

    suspend fun updateSensor(sensorId: String, data: SensorUpdated)

}