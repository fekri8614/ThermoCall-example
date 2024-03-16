package info.fekri8614.thermocall.util

sealed class MyScreens(val route: String) {
    data object EntryScreen: MyScreens("entryScreenFirst")
    data object SplashScreen: MyScreens("splashScreen")
    data object DashboardScreen: MyScreens("mainScreen")
    data object AboutUsScreen: MyScreens("aboutUsScreen")
    data object ProfileScreen: MyScreens("profileScreen")
    data object NoInternetScreen: MyScreens("noInternetScreen")
    data object SetupScreen: MyScreens("setupScreen")
}