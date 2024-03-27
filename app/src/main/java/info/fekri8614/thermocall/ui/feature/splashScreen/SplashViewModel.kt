package info.fekri8614.thermocall.ui.feature.splashScreen

import androidx.lifecycle.ViewModel
import info.fekri8614.thermocall.model.repository.user.UserRepository

class SplashViewModel(
    private val userRepository: UserRepository
): ViewModel() {

    fun isUserDataSaved(): Boolean {
        return (!userRepository.getUserEmail().isNullOrEmpty() && !userRepository.getUserPassword().isNullOrEmpty())
    }
}