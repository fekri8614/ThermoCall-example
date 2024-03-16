package info.fekri8614.thermocall.ui.feature.entry

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri8614.thermocall.ui.theme.BackgroundMain
import info.fekri8614.thermocall.util.MyEditText
import info.fekri8614.thermocall.util.MyScreens
import info.fekri8614.thermocall.util.NetworkChecker

@Composable
fun EntryScreen() {
    val viewModel = getNavViewModel<EntryViewModel>()
    val context = LocalContext.current
    val navigation = getNavController()
    val userName = viewModel.fullName.observeAsState("")

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = BackgroundMain,
                elevation = 1.dp,
                contentPadding = PaddingValues(start = 8.dp)
            ) {
                Text(
                    "ThermoCall",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight =
                        FontWeight.Bold
                    )
                )
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(8.dp, bottom = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Spacer(Modifier.height(10.dp))
                MyInput(viewModel)


                Button(
                    onClick = {
                        if (NetworkChecker(context).isInternetConnected) {
                            if (userName.value.isNotEmpty() || userName.value.isNotBlank()) {
                                // save user entries
                                viewModel.setData(userName.value)

                                if (viewModel.isUserDataSaved()) {
                                    navigation.navigate(MyScreens.DashboardScreen.route) {
                                        popUpTo(MyScreens.SplashScreen.route)
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Something went Wrong!",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }

                            } else {
                                Toast.makeText(
                                    context, "Please, check your Input out!", Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Please, check your Internet Connection!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.9f),
                ) {
                    Text(text = "Submit", modifier = Modifier.padding(6.dp))
                }
            }
        }
    )


}

@Composable
fun MyInput(viewModel: EntryViewModel) {
    val userName = viewModel.fullName.observeAsState("")

    MyEditText(edtValue = userName.value,
        icon = Icons.Default.Person,
        hint = "I'm ...",
        imeAction = ImeAction.Done,
        onValueChanges = { name ->
            viewModel.fullName.value = name
        })
}
