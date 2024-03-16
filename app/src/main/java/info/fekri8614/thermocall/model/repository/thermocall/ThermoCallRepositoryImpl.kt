package info.fekri8614.thermocall.model.repository.thermocall

import info.fekri8614.thermocall.model.data.ThermoCall
import info.fekri8614.thermocall.model.data.firebase.SendMessageDto
import info.fekri8614.thermocall.model.net.ApiService

class ThermoCallRepositoryImpl(
    private val apiService: ApiService
): ThermoCallRepository {
    override suspend fun getAllThermoCalls(): List<ThermoCall> {
        return apiService.getAllSensors()
    }

    override suspend fun sendMessage(body: SendMessageDto) {
        return apiService.sendMessage(body)
    }

    override suspend fun broadcast(body: SendMessageDto) {
        return apiService.broadcast(body)
    }
}