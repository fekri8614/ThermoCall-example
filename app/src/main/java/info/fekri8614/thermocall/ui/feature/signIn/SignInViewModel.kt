package info.fekri8614.thermocall.ui.feature.signIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.fekri8614.thermocall.model.repository.user.UserRepository
import info.fekri8614.thermocall.util.coroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class SignInViewModel(private val userRepository: UserRepository) : ViewModel() {
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    fun signInUser(loggingEvent: (String) -> Unit) {

        viewModelScope.launch(coroutineExceptionHandler) {
            // val result = userRepository.signIn(email.value!!, password.value!!)
            //LoggingEvent(result)

            delay(1.4.seconds)

            loggingEvent("success")

            print("USER_DATA = $loggingEvent")
        }

    }
}