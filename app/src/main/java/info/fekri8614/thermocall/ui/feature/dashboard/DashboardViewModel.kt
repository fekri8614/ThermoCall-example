package info.fekri8614.thermocall.ui.feature.dashboard

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import info.fekri8614.thermocall.model.data.ThermoCall
import info.fekri8614.thermocall.model.data.sensor.Sensor
import info.fekri8614.thermocall.model.repository.thermocall.SensorRepository
import info.fekri8614.thermocall.util.coroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

private const val TAG = "DashboardViewModel"

class DashboardViewModel(
    private val sensorRepository: SensorRepository
) : ViewModel() {
    val showProgress = mutableStateOf(false)
    val dataSensors = mutableStateOf<List<ThermoCall>>(listOf())
    val showDropDownMenu = mutableStateOf(false)

    val sensorId = MutableLiveData("")
    val sensorLabel = MutableLiveData("")
    val sensorMin = MutableLiveData("")
    val sensorMax = MutableLiveData("")

    val showNetDialog = mutableStateOf(false)
    val showAddSensorDialog = mutableStateOf(false)
    val showErrorMessage = mutableStateOf(false)

    val errorMessage = mutableStateOf("")

    private val fcmToken = mutableStateOf("")

    init {
        getAlarm()
        getDataFromNet()
    }

    fun sensorItemTextColor(currentTemp: Int, minTemp: Int, maxTemp: Int): Color {
        return if (currentTemp >= minTemp || currentTemp < maxTemp) {
            Color.White
        } else Color.Black
    }

    fun sensorItemBackgroundColor(currentTemp: Int, minTemp: Int, maxTemp: Int): Color {
        return if (currentTemp >= minTemp || currentTemp < maxTemp) {
            Color.Red
        } else Color.White
    }

    fun getAlarm() {
        viewModelScope.launch(coroutineExceptionHandler) {
            while (isActive) {
                try {
                    FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            fcmToken.value = task.result
                            Log.i(TAG, "qwer token => ${fcmToken.value}")
                        }
                    }
                    sensorRepository.alarmSensor(fcmToken = fcmToken.value)
                } catch (e: Exception) {
                    Log.e(TAG, "Occurred an ERROR: $e")
                }
                delay(6000) // Wait for 6 seconds before trying again
            }
        }
    }

    fun getDataFromNet() {
        viewModelScope.launch(coroutineExceptionHandler) {
            while (isActive) {
                try {
//                        showProgress.value = true
                    val sensorData = sensorRepository.getAllThermoCalls()
                    dataSensors.value = sensorData
//                        showProgress.value = false
                } catch (e: Exception) {
                    Log.e(TAG, "Error fetching data: ", e)
                    errorMessage.value = "Failed to fetch data: ${e.localizedMessage}"
                }
                delay(2000) // Wait for 2 seconds before trying again
            }
        }
    }

    fun createSensor(sensor: Sensor) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            try {
                sensorRepository.createSensor(sensor)
            } catch (e: Exception) {
                Log.e(TAG, "Couldn't create sensor: $e")
            }
        }
    }

    fun clearNewSensorData() {
        sensorId.value = ""
        sensorLabel.value = ""
        sensorMin.value = ""
        sensorMax.value = ""
    }
}