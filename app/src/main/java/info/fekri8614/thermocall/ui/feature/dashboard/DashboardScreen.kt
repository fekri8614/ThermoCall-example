package info.fekri8614.thermocall.ui.feature.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.DrawerValue
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri8614.thermocall.ui.theme.BackgroundMain
import info.fekri8614.thermocall.ui.theme.PrimaryDarkColor
import info.fekri8614.thermocall.util.ShowAlertDialog
import info.fekri8614.thermocall.util.ShowWithBodyDialog
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen() {
    val dashboardWidget = DashboardWidget()

    val context = LocalContext.current
    val viewModel = getNavViewModel<DashboardViewModel>()
    val navController = getNavController()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scaffoldState = rememberScaffoldState(drawerState = drawerState)
    val scope = rememberCoroutineScope()

    val dataSensors = viewModel.dataSensors.value

    val uiController = rememberSystemUiController()

    SideEffect {
        uiController.setStatusBarColor(PrimaryDarkColor)
    }

    dashboardWidget.apply {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { scaffoldState.drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    title = {
                        Text("ThermoCall")
                    },
                    elevation = 1.dp,
                )
            },
            drawerContent = {
                DrawerContent { id ->
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }

                    navController.navigate(id)
                }
            },
            backgroundColor = BackgroundMain,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { viewModel.showAddSensorDialog.value = true },
                    content = {
                        Icon(
                            Icons.Default.AddCircle,
                            contentDescription = "Add New Device",
                            modifier = Modifier.size(60.dp)
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
                    MainScreenBody(
                        modifier = Modifier.padding(it),
                        viewModel = viewModel,
                        context = context,
                        dataSensor = dataSensors,
                        onSensorClicked = { id -> println("THE_ID ==> $id") }
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
                    ShowWithBodyDialog(
                        title = "Add New Sensor",
                        body = {
                            CreateNewSensor(
                                sensorId = "",
                                label = "",
                                min = 0,
                                max = 0,
                                onSensorIdChanged = {},
                                onLabelChanged = {},
                                onMinChanged = {},
                                onMaxChanged = {}
                            )
                        },
                        btnMsg = "Confirm",
                        onConfirmClicked = {
                            viewModel.showAddSensorDialog.value = false
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
