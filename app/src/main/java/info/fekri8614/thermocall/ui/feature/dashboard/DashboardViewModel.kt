package info.fekri8614.thermocall.ui.feature.dashboard

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.fekri8614.thermocall.model.data.ThermoCall
import info.fekri8614.thermocall.model.data.firebase.ChatState
import info.fekri8614.thermocall.model.data.firebase.NotificationBody
import info.fekri8614.thermocall.model.data.firebase.SendMessageDto
import info.fekri8614.thermocall.model.data.sensor.Sensor
import info.fekri8614.thermocall.model.repository.thermocall.SensorRepository
import info.fekri8614.thermocall.util.coroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DashboardViewModel(
    private val sensorRepository: SensorRepository,
) : ViewModel() {
    val showProgress = mutableStateOf(false)
    val dataSensors = mutableStateOf<List<ThermoCall>>(listOf())

    val sensorId = MutableLiveData("")
    val sensorLabel = MutableLiveData("")
    val sensorMin = MutableLiveData(0)
    val sensorMax = MutableLiveData(0)

    val showNetDialog = mutableStateOf(false)
    val showAddSensorDialog = mutableStateOf(false)
    val showErrorMessage = mutableStateOf(false)

    val errorMessage = mutableStateOf("")

    var state by mutableStateOf(ChatState())
        private set

    init {
        getDataFromNet()
        setMessage(isBroadcast = true)
    }

    fun getDataFromNet() {
        viewModelScope.launch(coroutineExceptionHandler) {
            while (isActive) {
                try {
                    showProgress.value = true

                    val sensorData = sensorRepository.getAllThermoCalls()
                    dataSensors.value = sensorData

                    showProgress.value = false
                } catch (e: Exception) {
                    Log.e("DashboardViewModel", "Error fetching data: ", e)
                    errorMessage.value = "Failed to fetch data: ${e.localizedMessage}"
                }
                delay(5000) // Wait for 5 seconds before trying again
            }
        }
    }

    fun createSensor(sensor: Sensor) {
        viewModelScope.launch(coroutineExceptionHandler) {
            try {
                sensorRepository.createSensor(sensor)
            } catch (e: Exception) {
                Log.e("DashboardViewModel", "Couldn't create sensor: $e")
            }
        }
    }
    fun clearNewSensorData() {
        sensorId.value = ""
        sensorLabel.value = ""
        sensorMin.value = 0
        sensorMax.value = 0
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

    fun onMessageChange(message: String) {
        state = state.copy(
            messageText = message
        )
    }

    fun setMessage(isBroadcast: Boolean) {
        viewModelScope.launch {

            val messageDto = SendMessageDto(
                to = if (isBroadcast) null else state.remoteToken,
                notification = NotificationBody(
                    title = "New message!",
                    body = state.messageText
                )
            )

            try {
                if (isBroadcast) {
                    sensorRepository.broadcast(messageDto)
                } else {
                    sensorRepository.sendMessage(messageDto)
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