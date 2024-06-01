package info.fekri8614.thermocall.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

//private val LightColors = lightColors(
//    primary = PrimaryDarkColor, secondary = SecondaryColor, background = PrimaryColor
//)

private val LightColorPalette = lightColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryVariant,
    secondary = SecondaryColor,
    background = BackgroundColor,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun ThermoCallTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colors = LightColorPalette, typography = Typography, content = content, shapes = Shapes
    )

    val uiController = rememberSystemUiController()

    SideEffect {
        uiController.apply {
            setStatusBarColor(BackgroundColor)
            setNavigationBarColor(BackgroundColorV2)
        }
    }

}
