package info.fekri8614.thermocall.ui.feature.splashScreen

import android.os.Handler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri8614.thermocall.util.MyScreens

@Composable
fun SplashScreen(isFirstTime: Boolean) {
    val viewModel = getNavViewModel<SplashViewModel>()
    val navigation = getNavController()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
            Text("Loading ...")

            Handler().postDelayed({
                if (isFirstTime && !viewModel.isUserDataSaved()) navigation.navigate(MyScreens.SignUpScreen.route)
                else navigation.navigate(MyScreens.DashboardScreen.route) { popUpTo(MyScreens.SplashScreen.route) }
            }, 3500)
        }
    }
}