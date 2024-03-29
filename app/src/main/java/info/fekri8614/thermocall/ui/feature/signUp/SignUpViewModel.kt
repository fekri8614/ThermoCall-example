package info.fekri8614.thermocall.ui.feature.signUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import info.fekri8614.thermocall.model.data.SignInUiState
import info.fekri8614.thermocall.model.repository.user.UserRepository
import info.fekri8614.thermocall.util.coroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class SignUpViewModel(private val userRepository: UserRepository, private val firebaseAuth: FirebaseAuth) : ViewModel() {
    val name = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")

    fun signUpUser(data: SignInUiState, onUserAdded: (Task<AuthResult>) -> Unit) {
        userRepository.signUpUser(
            auth = firebaseAuth,
            email = data.email,
            pass = data.password,
            onUserAdded = onUserAdded
        )
    }

    fun saveUserData(data: SignInUiState) {
        userRepository.saveUserEmail(data.email)
        userRepository.saveUserPassword(data.password)
    }

}