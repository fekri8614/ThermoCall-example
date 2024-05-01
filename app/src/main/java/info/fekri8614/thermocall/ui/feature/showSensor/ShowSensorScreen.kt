package info.fekri8614.thermocall.ui.feature.showSensor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShowSensorScreen(sensorId: String) {
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        Text("Hello, world")
        Text("Sensor ID => $sensorId")
    }
}