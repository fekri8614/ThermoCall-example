package info.fekri8614.thermocall.ui.feature.setup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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

@Composable
fun SetupScreen(sensorId: String) {

    val context = LocalContext.current
    val navController = getNavController()

    val viewModel = getNavViewModel<SetupViewModel>()
    val setupWidget = SetupWidget(viewModel)
    viewModel.getSensorData(sensorId)
    val sensorLabel = viewModel.sensorLabel.observeAsState()

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
                    .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                Row(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text("-65", style = TextStyle(fontSize = 18.sp))
                    Card(
                        backgroundColor = CardBackground,
                        shape = CircleShape,
                    ) {
                        Text("-60", style = TextStyle(fontSize = 18.sp), modifier = Modifier.padding(16.dp))
                    }
                    Text("-50", style = TextStyle(fontSize = 18.sp))
                }

            }
        }
    }
}
