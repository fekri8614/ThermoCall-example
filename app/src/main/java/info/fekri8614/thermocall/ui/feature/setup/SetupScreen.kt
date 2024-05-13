package info.fekri8614.thermocall.ui.feature.setup

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.RangeSlider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri8614.thermocall.ui.theme.CardBackground
import info.fekri8614.thermocall.ui.theme.PrimaryDarkColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SetupScreen(sensorId: String) {

    val context = LocalContext.current
    val navController = getNavController()

    val viewModel = getNavViewModel<SetupViewModel>()
    val setupWidget = SetupWidget(viewModel)
    viewModel.getSensorData(sensorId)
    val sensorLabel = viewModel.sensorLabel.observeAsState()
    val sensorMinTemp = viewModel.sensorMinTemp.observeAsState()
    val sensorMaxTemp = viewModel.sensorMaxTemp.observeAsState()

    setupWidget.apply {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    title = { Text("Setup") },
                    navigationIcon = {
                        IconButton(onClick = {
                            viewModel.clearData()
                            navController.popBackStack()
                        }) {
                            Icon(Icons.Default.ArrowBack, "Back")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            viewModel.menuExpanded.value = !viewModel.menuExpanded.value
                        }) {
                            Icon(Icons.Default.MoreVert, "More")
                        }
                        DropdownMenu(
                            expanded = viewModel.menuExpanded.value,
                            onDismissRequest = { viewModel.menuExpanded.value = false }
                        ) {
                            DropdownMenuItem(content = { Text("Edit") }, onClick = {
                                viewModel.menuExpanded.value = false
                                Toast.makeText(context, "Edit is clicked", Toast.LENGTH_SHORT)
                                    .show()
                            })
                            DropdownMenuItem(modifier = Modifier.background(Color.Red),
                                content = { Text("Delete", color = Color.White) },
                                onClick = {
                                    viewModel.menuExpanded.value = false
                                    viewModel.deleteSensor(sensorId)
                                    navController.popBackStack()
                                })
                        }
                    },
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
                    .padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column() {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text("ID: ${viewModel.sensorIdData.value.sensorId}")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    MainTextField(
                        edtValue = sensorLabel.value ?: "",
                        hint = "Sensor label"
                    ) { label ->
                        viewModel.sensorLabel.value = label
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text("Set Temperature Range")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(0.7f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text("${sensorMinTemp.value}", style = TextStyle(fontSize = 18.sp))
                            Card(
                                backgroundColor = CardBackground,
                                shape = CircleShape,
                            ) {
                                Text(
                                    "-60",
                                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Black),
                                    modifier = Modifier.padding(22.dp)
                                )
                            }
                            Text("${sensorMaxTemp.value}", style = TextStyle(fontSize = 18.sp))
                        }

                        RangeSlider(
                            value = viewModel.tempSliderPosition.value,
                            onValueChange = {
                                viewModel.tempSliderPosition.value = it
                                viewModel.sensorMinTemp.value = it.start.toInt()
                                viewModel.sensorMaxTemp.value = it.endInclusive.toInt()
                            },
                            valueRange = (-80f..50f),
                            enabled = true,
                            steps = 100,
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    OutlinedButton(onClick = {
                        Toast.makeText(context, "Cancel clicked", Toast.LENGTH_SHORT).show()
                    }, modifier = Modifier.fillMaxWidth(0.45f), border = BorderStroke(2.dp, PrimaryDarkColor)
                    ) {
                        Text("Cancel", modifier = Modifier.padding(4.dp))
                    }
                    Button(onClick = {
                        viewModel.onDataUpdated(
                            sensorId = sensorId,
                            newLabel = viewModel.sensorLabel.value,
                            newMin = viewModel.tempSliderPosition.value.start,
                            newMax = viewModel.tempSliderPosition.value.endInclusive
                        )
                    }, modifier = Modifier.fillMaxWidth(0.8f)) {
                        Text("Save", modifier = Modifier.padding(4.dp))
                    }
                }

            }
        }
    }
}
