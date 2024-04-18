package info.fekri8614.thermocall.ui.feature.dashboard

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import info.fekri8614.thermocall.R
import info.fekri8614.thermocall.model.data.ThermoCall
import info.fekri8614.thermocall.ui.theme.BackgroundMain
import info.fekri8614.thermocall.ui.theme.PrimaryDarkColor
import info.fekri8614.thermocall.ui.theme.Shapes
import info.fekri8614.thermocall.util.MyAnimShower
import info.fekri8614.thermocall.util.MyScreens
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
        Box {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (viewModel.showProgress.value)
                    LinearProgressIndicator(modifier = modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(8.dp))

                if (NetworkChecker(context).isInternetConnected) {
                    SensorItemList(data = dataSensor) { id ->
                        if (NetworkChecker(context).isInternetConnected) {
                            onSensorClicked.invoke(id)
                        } else {
                            viewModel.showNetDialog.value = true
                        }
                    }
                } else {
                    MyAnimShower(name = R.raw.loading_anim)
                    viewModel.showNetDialog.value = true
                }
            }
        }
    }

    @Composable
    fun SensorItemList(
        modifier: Modifier = Modifier,
        data: List<ThermoCall>,
        onSensorClicked: (String) -> Unit,
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth(),
            color = BackgroundMain
        ) {
            LazyColumn(
                modifier = modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(data.size) { index ->
                    SensorItem(onSensorClicked = onSensorClicked, data = data[index])
                }
            }
        }
    }

    @Composable
    fun SensorItem(
        modifier: Modifier = Modifier,
        onSensorClicked: (String) -> Unit,
        data: ThermoCall
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth(0.9f)
                .height(100.dp)
                .padding(top = 16.dp)
                .clickable {
                    onSensorClicked.invoke(data.sensorId)
                },
            border = BorderStroke(2.dp, Color.Black),
            elevation = 3.dp,
            backgroundColor = BackgroundMain
        ) {
            Row(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(data.label)
                Column {
                    Text("Min: ${data.min}")
                    Text("Max: ${data.max}")
                }
            }
        }
    }

    @Composable
    fun DrawerContent(onItemClicked: (String) -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundMain),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .background(PrimaryDarkColor)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "ThermoCall", style = TextStyle(
                                fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White
                            )
                        )
                        Text(
                            text = "A real-time temperature monitoring and alarming system for lab freezers",
                            color = Color.White
                        )
                    }

                }

                Spacer(modifier = Modifier.height(10.dp))

                DrawerItem(text = "Profile",
                    onItemClick = { onItemClicked.invoke(MyScreens.ProfileScreen.route) })

                DrawerItem(text = "Setup",
                    onItemClick = { onItemClicked.invoke(MyScreens.SetupScreen.route) })

                DrawerItem(text = "About Us",
                    onItemClick = { onItemClicked.invoke(MyScreens.AboutUsScreen.route) })
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Developed by ", fontSize = 14.sp, color = Color.Black
                )
                Text(
                    text = "ThermoCall co.",
                    fontSize = 16.sp,
                    color = PrimaryDarkColor,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }

    @Composable
    fun DrawerItem(text: String, onItemClick: () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clickable { onItemClick() },
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(start = 16.dp)
            )
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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
        min: Int,
        max: Int,
        onSensorIdChanged: (String) -> Unit,
        onLabelChanged: (String) -> Unit,
        onMinChanged: (Int) -> Unit,
        onMaxChanged: (Int) -> Unit
    ) {
        MainTextField(edtValue = sensorId, icon = Icons.Default.Create, hint = "Sensor Id") { id ->
            onSensorIdChanged.invoke(id)
        }
        MainTextField(edtValue = label, icon = Icons.Default.Create, hint = "Label") { nLabel ->
            onLabelChanged.invoke(nLabel)
        }
        MainTextField(edtValue = min.toString(), icon = Icons.Default.Create, hint = "Min") { id ->
            onMinChanged.invoke(id.toInt())
        }
        MainTextField(edtValue = max.toString(), icon = Icons.Default.Create, hint = "Max") { id ->
            onMaxChanged.invoke(id.toInt())
        }
    }
}