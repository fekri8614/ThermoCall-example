package info.fekri8614.thermocall.ui.feature.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.fekri8614.thermocall.model.data.ThermoCall
import info.fekri8614.thermocall.model.data.firebase.ChatState
import info.fekri8614.thermocall.model.data.firebase.NotificationBody
import info.fekri8614.thermocall.model.data.firebase.SendMessageDto
import info.fekri8614.thermocall.model.repository.thermocall.ThermoCallRepository
import info.fekri8614.thermocall.util.coroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DashboardViewModel(
    private val thermoCallRepository: ThermoCallRepository,
) : ViewModel() {
    val showProgress = mutableStateOf(false)
    val dataSensors = mutableStateOf<List<ThermoCall>>(listOf())
    val showNetDialog = mutableStateOf(false)

    var state by mutableStateOf(ChatState())
        private set

    init {
        getDataFromNet()
        setMessage(isBroadcast = true)
    }

    fun getDataFromNet() {
        viewModelScope.launch(coroutineExceptionHandler) {
            showProgress.value = true

            val sensorData = thermoCallRepository.getAllThermoCalls()
            dataSensors.value = sensorData

            showProgress.value = false
        }
    }

    fun onRemoteTokenChanged(newToken: String) {
        state = state.copy(
            remoteToken = newToken
        )
    }

    fun onSubmitRemoteToken() {
        state = state.copy(
            isEnteringToken = false
        )
    }

    fun onMessageChange(message:String) {
        state = state.copy(
            messageText = message
        )
    }

    fun setMessage(isBroadcast: Boolean) {
        viewModelScope.launch {

            val messageDto = SendMessageDto(
                to = if(isBroadcast) null else state.remoteToken,
                notification = NotificationBody(
                    title = "New message!",
                    body = state.messageText
                )
            )

            try {
                if(isBroadcast) {
                    thermoCallRepository.broadcast(messageDto)
                } else {
                    thermoCallRepository.sendMessage(messageDto)
                }

                state = state.copy(
                    messageText = ""
                )
            } catch (e: HttpException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

}