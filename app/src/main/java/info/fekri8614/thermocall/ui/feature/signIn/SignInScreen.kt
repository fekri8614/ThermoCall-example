package info.fekri8614.thermocall.ui.feature.signIn

import android.os.Handler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri8614.thermocall.R
import info.fekri8614.thermocall.util.MyAnimShower
import info.fekri8614.thermocall.util.MyScreens

@Composable
fun SignInScreen(isUserFirst: Boolean) {
    val context = LocalContext.current
    val navigation = getNavController()
    val viewModel = getNavViewModel<SplashScreenViewModel>()


    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ) {
        MyAnimShower(R.raw.loading_anim)

        Text(
            text = "Welcome ...",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
        )

        Handler().postDelayed({
            if (isUserFirst && !viewModel.isUserSaved()) navigation.navigate(MyScreens.EntryScreen.route)
            else navigation.navigate(MyScreens.DashboardScreen.route)
        }, 3500)
    }
}
