package info.fekri8614.thermocall.ui.feature.setup

import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.fekri8614.thermocall.model.data.sensor.Sensor
import info.fekri8614.thermocall.model.data.sensor.SensorTemperature
import info.fekri8614.thermocall.model.data.sensor.SensorUpdated
import info.fekri8614.thermocall.model.repository.thermocall.SensorRepository
import info.fekri8614.thermocall.util.coroutineExceptionHandler
import kotlinx.coroutines.launch

class SetupViewModel(
    private val sensorRepository: SensorRepository
) : ViewModel() {
    val menuExpanded = mutableStateOf(false)

    val sensorHistoryData = mutableStateOf<List<SensorTemperature>>(arrayListOf())
    val sensorIdData = mutableStateOf(Sensor("", "", "", ""))
    val sensorLabel = MutableLiveData("")
    val sensorMinTemp = MutableLiveData(0)
    val sensorMaxTemp = MutableLiveData(0)

    val tempSliderPosition = mutableStateOf<ClosedFloatingPointRange<Float>>(0f..0f)

    fun clearData() {
        sensorLabel.value = ""
    }

    fun getSensorData(sensorId: String) {
        synchronized("") {
            viewModelScope.launch(coroutineExceptionHandler) {
                sensorIdData.value = sensorRepository.getSensorById(sensorId)
                sensorLabel.value = sensorIdData.value.label
                tempSliderPosition.value = ((sensorIdData.value.min.toFloat())..(sensorIdData.value.max.toFloat()))
                sensorMinTemp.value = tempSliderPosition.value.start.toInt()
                sensorMaxTemp.value = tempSliderPosition.value.endInclusive.toInt()
                sensorHistoryData.value = sensorRepository.getSensorHistory(sensorId)
            }
        }
    }

    fun onDataUpdated(sensorId: String, newLabel: String?, newMin: Float?, newMax: Float?) {
        viewModelScope.launch(coroutineExceptionHandler) {
            sensorRepository.updateSensor(
                sensorId = sensorId,
                data = SensorUpdated(
                    label = newLabel ?: sensorIdData.value.label,
                    min = newMin?.toInt() ?: sensorIdData.value.min.toInt(),
                    max = newMax?.toInt() ?: sensorIdData.value.max.toInt(),
                )
            )
        }
    }

    fun deleteSensor(sensorId: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            sensorRepository.deleteSensorById(sensorId = sensorId)
        }
    }
}