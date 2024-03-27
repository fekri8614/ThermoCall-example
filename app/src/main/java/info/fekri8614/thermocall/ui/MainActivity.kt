package info.fekri8614.thermocall.ui

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.navigation.KoinNavHost
import info.fekri8614.thermocall.di.myModule
import info.fekri8614.thermocall.ui.feature.aboutUs.AboutUsScreen
import info.fekri8614.thermocall.ui.feature.dashboard.DashboardScreen
import info.fekri8614.thermocall.ui.feature.signUp.SignUpScreen
import info.fekri8614.thermocall.ui.feature.profile.ProfileScreen
import info.fekri8614.thermocall.ui.feature.setup.SetupScreen
import info.fekri8614.thermocall.ui.feature.signIn.SignInScreen
import info.fekri8614.thermocall.ui.feature.splashScreen.SplashScreen
import info.fekri8614.thermocall.ui.theme.BackgroundMain
import info.fekri8614.thermocall.ui.theme.ThermoCallTheme
import info.fekri8614.thermocall.util.IS_USER_FIRST_TIME
import info.fekri8614.thermocall.util.MyScreens
import org.koin.android.ext.koin.androidContext
import kotlin.properties.Delegates

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set directionality
        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
        // Request permissions
        requestNotificationPermission()

        // Check if user's first time
        val sharedPreferences = getSharedPreferences("my_fist_t_checker_sh", Context.MODE_PRIVATE)
        val isFirstTime = sharedPreferences.getBoolean(IS_USER_FIRST_TIME, true)
        if (isFirstTime) {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean(IS_USER_FIRST_TIME, false)
            editor.apply()
        }

        // UI stuff
        setContent {
            Koin(
                appDeclaration = {
                    androidContext(this@MainActivity)
                    modules(myModule)
                }
            ) {
                ThermoCallTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(), color = BackgroundMain
                    ) {
                        MainAppUi(isFirstTime)
                    }
                }
            }
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val hasPermission = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!hasPermission) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    0
                )
            }
        }
    }
}

@Composable
fun MainAppUi(isFirstTime: Boolean) {
    val controller = rememberNavController()

    KoinNavHost(navController = controller, startDestination = MyScreens.SplashScreen.route) {

        composable(route = MyScreens.SignInScreen.route) {
            SignInScreen()
        }

        composable(route = MyScreens.SignUpScreen.route) {
            SignUpScreen()
        }

        composable(route = MyScreens.DashboardScreen.route) {
            DashboardScreen()
        }

        composable(route = MyScreens.AboutUsScreen.route) {
            AboutUsScreen()
        }

        composable(route = MyScreens.ProfileScreen.route) {
            ProfileScreen()
        }

        composable(route = MyScreens.SplashScreen.route) {
            SplashScreen(isFirstTime)
        }

        composable(route = MyScreens.NoInternetScreen.route) {
            NoInternetScreen()
        }

        composable(route = MyScreens.SetupScreen.route) {
            SetupScreen()
        }
    }
}

@Composable
fun NoInternetScreen() {
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(BackgroundMain)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(
                Icons.Default.Search,
                contentDescription = "Attention",
                modifier = Modifier
                    .size(32.dp)
                    .padding(8.dp),
                tint = Color.Red,
            )

            Text("Looking for Internet Connection!")
        }
    }
}
