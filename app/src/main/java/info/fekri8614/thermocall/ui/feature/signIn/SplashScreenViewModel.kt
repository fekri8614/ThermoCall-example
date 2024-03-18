package info.fekri8614.thermocall.ui.feature.signIn

import androidx.lifecycle.ViewModel
import info.fekri8614.thermocall.model.repository.user.UserRepository

class SplashScreenViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun isUserSaved(): Boolean = (!userRepository.getUserEmail().isNullOrEmpty())
}