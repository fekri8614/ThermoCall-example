package info.fekri8614.thermocall.model.repository.thermocall

import info.fekri8614.thermocall.model.data.ThermoCall
import info.fekri8614.thermocall.model.data.firebase.SendMessageDto
import info.fekri8614.thermocall.model.data.sensor.Sensor

interface SensorRepository {
    suspend fun getAllThermoCalls(): List<ThermoCall>

    suspend fun sendMessage(body: SendMessageDto)

    suspend fun broadcast(body: SendMessageDto)

    suspend fun createSensor(body: Sensor)

}