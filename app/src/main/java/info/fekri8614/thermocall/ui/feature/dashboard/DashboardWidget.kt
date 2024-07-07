package info.fekri8614.thermocall.ui.feature.dashboard

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import info.fekri8614.thermocall.R
import info.fekri8614.thermocall.model.data.ThermoCall
import info.fekri8614.thermocall.ui.theme.BackgroundColor
import info.fekri8614.thermocall.ui.theme.SensorItemBackground
import info.fekri8614.thermocall.ui.theme.Shapes
import info.fekri8614.thermocall.util.MyAnimShower
import info.fekri8614.thermocall.util.MyDateFormatter
import info.fekri8614.thermocall.util.NetworkChecker

class DashboardWidget {

    @Composable
    fun MainScreenBody(
        modifier: Modifier,
        viewModel: DashboardViewModel,
        context: Context,
        dataSensor: List<ThermoCall>,
        onSensorClicked: (String) -> Unit
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (NetworkChecker(context).isInternetConnected) {
                SensorItemList(
                    data = dataSensor,
                    onSensorClicked = { id ->
                        if (NetworkChecker(context).isInternetConnected) {
                            onSensorClicked.invoke(id)
                        } else {
                            viewModel.showNetDialog.value = true
                        }
                    },
                    viewModel = viewModel
                )
            } else {
                MyAnimShower(name = R.raw.loading_anim)
                viewModel.showNetDialog.value = true
            }
        }
    }

    @Composable
    fun SensorItemList(
        modifier: Modifier = Modifier,
        data: List<ThermoCall>,
        onSensorClicked: (String) -> Unit,
        viewModel: DashboardViewModel
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth(),
            color = BackgroundColor
        ) {
            LazyColumn(
                modifier = modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(data.size) { index ->
                    SensorItem(
                        onSensorClicked = onSensorClicked,
                        data = data[index],
                        viewModel = viewModel,
                    )
                }
            }
        }
    }

    @Composable
    fun SensorItem(
        modifier: Modifier = Modifier,
        onSensorClicked: (String) -> Unit,
        data: ThermoCall,
        viewModel: DashboardViewModel,
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth(0.95f)
                .height(110.dp)
                .padding(top = 16.dp)
                .clickable {
                    onSensorClicked.invoke(data.id)
                },
            elevation = 1.dp,
            backgroundColor = SensorItemBackground
        ) {
            Row(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        data.label,
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium)
                    )
                    if (data.currentTemperature != null)
                        Text(
                            (MyDateFormatter().getMinuteValue(
                                data.currentTemperature.timestamp
                            )).toString() + " minutes ago", fontSize = 12.sp
                        )
                }

                Row(
                    modifier = Modifier.width(130.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text("${data.min}", style = TextStyle(fontSize = 18.sp))
                    if (data.currentTemperature != null) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(
                                    viewModel.sensorItemBackgroundColor(
                                        currentTemp = data.currentTemperature.temperature,
                                        minTemp = data.min,
                                        maxTemp = data.max
                                    )
                                )
                        ) {
                            Text(
                                data.currentTemperature.temperature.toString(),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Black,
                                    color = viewModel.sensorItemTextColor(
                                        currentTemp = data.currentTemperature.temperature,
                                        minTemp = data.min,
                                        maxTemp = data.max
                                    )
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape).background(Color.White)
                        ) {
                            Text(
                                "no-temp set",
                                style = TextStyle(
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Black,
                                ),
                            )
                        }
                    }
                    Text("${data.max}", style = TextStyle(fontSize = 18.sp))
                }
            }
        }
    }

    @Composable
    fun MainTextField(
        edtValue: String,
        icon: ImageVector,
        hint: String,
        onValueChanges: (String) -> Unit
    ) {
        OutlinedTextField(
            label = { Text(hint) },
            value = edtValue,
            singleLine = true,
            onValueChange = onValueChanges,
            placeholder = { Text(hint) },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 12.dp),
            shape = Shapes.medium,
            leadingIcon = { Icon(icon, null) }
        )
    }

    @Composable
    fun CreateNewSensor(
        sensorId: String,
        label: String,
        min: String,
        max: String,
        onSensorIdChanged: (String) -> Unit,
        onLabelChanged: (String) -> Unit,
        onMinChanged: (String) -> Unit,
        onMaxChanged: (String) -> Unit
    ) {
        MainTextField(edtValue = sensorId, icon = Icons.Default.Create, hint = "Sensor Id") { id ->
            onSensorIdChanged.invoke(id)
        }
        MainTextField(edtValue = label, icon = Icons.Default.Create, hint = "Label") { nLabel ->
            onLabelChanged.invoke(nLabel)
        }
        MainTextField(edtValue = min, icon = Icons.Default.Create, hint = "Min") { nMin ->
            onMinChanged.invoke(nMin)
        }
        MainTextField(edtValue = max, icon = Icons.Default.Create, hint = "Max") { nMax ->
            onMaxChanged.invoke(nMax)
        }
    }

    @Composable
    fun DropDownMenuItem(
        title: String,
        icon: ImageVector,
        backgroundColor: Color = MaterialTheme.colors.background,
        showTick: Boolean = false,
        onClicked: () -> Unit
    ) {
        DropdownMenuItem(
            modifier = Modifier
                .size(width = 200.dp, height = 60.dp)
                .background(backgroundColor),
            onClick = onClicked,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (showTick) Icon(Icons.Default.Check, contentDescription = "")
                Text(title)
                Icon(icon, contentDescription = title)
            }
        }
    }
}