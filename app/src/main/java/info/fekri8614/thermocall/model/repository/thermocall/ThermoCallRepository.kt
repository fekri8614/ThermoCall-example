package info.fekri8614.thermocall.model.repository.thermocall

import info.fekri8614.thermocall.model.data.ThermoCall
import info.fekri8614.thermocall.model.data.firebase.SendMessageDto

interface ThermoCallRepository {
    suspend fun getAllThermoCalls(): List<ThermoCall>

    suspend fun sendMessage(body: SendMessageDto)

    suspend fun broadcast(body: SendMessageDto)

}