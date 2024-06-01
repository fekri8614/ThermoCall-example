package info.fekri8614.thermocall.ui.feature.dashboard

import android.content.res.Configuration
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri8614.thermocall.model.data.sensor.Sensor
import info.fekri8614.thermocall.ui.theme.BackgroundColor
import info.fekri8614.thermocall.ui.theme.BackgroundColorV2
import info.fekri8614.thermocall.util.MyScreens
import info.fekri8614.thermocall.util.ShowAlertDialog
import info.fekri8614.thermocall.util.ShowWithBodyDialog

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardScreen() {
    val dashboardWidget = DashboardWidget()

    val context = LocalContext.current
    val viewModel = getNavViewModel<DashboardViewModel>()
    val navController = getNavController()

    val dataSensors = viewModel.dataSensors.value

    val config = LocalConfiguration.current

    dashboardWidget.apply {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Color.White,
                    actions = {
                        IconButton(onClick = {
                            viewModel.showDropDownMenu.value = !viewModel.showDropDownMenu.value
                        }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                        }
                        DropdownMenu(
                            expanded = viewModel.showDropDownMenu.value,
                            onDismissRequest = { viewModel.showDropDownMenu.value = false },

                        ) {
                            DropDownMenuItem(
                                title = "Edit List",
                                icon = Icons.Default.Create,
                                onClicked = {}
                            )
                            DropDownMenuItem(
                                title = "Celsius",
                                icon = Icons.Default.Star,
                                backgroundColor = BackgroundColorV2,
                                showTick = true,
                                onClicked = {}
                            )
                            DropDownMenuItem(
                                title = "Fahrenheit",
                                icon = Icons.Default.FavoriteBorder,
                                onClicked = {}
                            )
                            DropDownMenuItem(
                                title = "Report Issue",
                                icon = Icons.Default.Send,
                                onClicked = {}
                            )
                        }
                    },
                    title = {
                        val padding = if(config.orientation == Configuration.ORIENTATION_LANDSCAPE) 0.dp else ((config.screenWidthDp.dp) / 3)
                        Text("ThermoCall", textAlign = TextAlign.Center, modifier = Modifier.padding(start = padding))
                    },
                    elevation = 0.dp,
                )
            },
            bottomBar = {

            },
            backgroundColor = BackgroundColor,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { viewModel.showAddSensorDialog.value = true },
                    content = {
                        Icon(
                            Icons.Rounded.Add,
                            contentDescription = "Add New Device",
                            modifier = Modifier.size(40.dp),
                            tint = Color.White
                        )
                    }
                )
            },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true,
            content = {
                if (viewModel.showErrorMessage.value && viewModel.errorMessage.value.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Red)
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = viewModel.errorMessage.value,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.error,
                                fontSize = 20.sp
                            )
                        )
                    }
                } else {
                    /// TODO: Set the condition background color
                    MainScreenBody(
                        modifier = Modifier.padding(it),
                        viewModel = viewModel,
                        context = context,
                        dataSensor = dataSensors,
                        onSensorClicked = { id ->
                            navController.navigate(MyScreens.SetupScreen.route + "/$id")
                        }
                    )
                }

                if (viewModel.showNetDialog.value) {
                    ShowAlertDialog(
                        title = "Check your Connection!",
                        msg = "Please, check your internet connection out!",
                        btnMsg = "Try Again",
                        onConfirmClicked = {
                            viewModel.showNetDialog.value = false
                            viewModel.getDataFromNet()
                        },
                        onDismissRequest = {
                            viewModel.showNetDialog.value = false
                        }
                    )
                }
                if (viewModel.showAddSensorDialog.value) {
                    val nSensorId = viewModel.sensorId.observeAsState("")
                    val nSensorLabel = viewModel.sensorLabel.observeAsState("")
                    val nSensorMin = viewModel.sensorMin.observeAsState("")
                    val nSensorMax = viewModel.sensorMax.observeAsState("")
                    ShowWithBodyDialog(
                        title = "Add New Sensor",
                        body = {
                            CreateNewSensor(
                                sensorId = nSensorId.value,
                                label = nSensorLabel.value,
                                min = nSensorMin.value,
                                max = nSensorMax.value,
                                onSensorIdChanged = { nId -> viewModel.sensorId.value = nId },
                                onLabelChanged = { nLabel -> viewModel.sensorLabel.value = nLabel },
                                onMinChanged = { nMin -> viewModel.sensorMin.value = nMin },
                                onMaxChanged = { nMax -> viewModel.sensorMax.value = nMax }
                            )
                        },
                        btnMsg = "Confirm",
                        onConfirmClicked = {
                            if (
                                viewModel.sensorId.value!!.isNotEmpty() && viewModel.sensorId.value!!.isNotBlank() && !viewModel.sensorId.value!!.contains(
                                    " "
                                ) &&
                                viewModel.sensorLabel.value!!.isNotEmpty() && viewModel.sensorLabel.value!!.isNotBlank() &&
                                viewModel.sensorMin.value!!.isNotEmpty() && viewModel.sensorMin.value.toString()
                                    .isNotBlank() &&
                                viewModel.sensorMax.value!!.isNotEmpty() && viewModel.sensorMax.value.toString()
                                    .isNotBlank()
                            ) {
                                val newSensor = Sensor(
                                    sensorId = viewModel.sensorId.value!!,
                                    label = viewModel.sensorLabel.value!!,
                                    min = viewModel.sensorMin.value!!,
                                    max = viewModel.sensorMax.value!!,
                                    currentTemperature = null,
                                )
                                viewModel.createSensor(newSensor)
                                viewModel.clearNewSensorData()
                                viewModel.showAddSensorDialog.value = false
                                viewModel.showAddSensorDialog.value = false
                                Toast.makeText(
                                    context,
                                    "Couldn't create sensor",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        onDismissRequest = {
                            viewModel.showAddSensorDialog.value = false
                        }
                    )
                }
            }
        )
    }

}
