package info.fekri8614.thermocall.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColors = lightColors(
    primary = PrimaryDarkColor, secondary = PrimaryDarkColor, background = BackgroundMain
)

@Composable
fun ThermoCallTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colors = LightColors, typography = Typography, content = content, shapes = Shapes
    )

    val uiController = rememberSystemUiController()

    SideEffect {
        uiController.setSystemBarsColor(BackgroundMain)
        uiController.setNavigationBarColor(BackgroundMain)
    }


}
