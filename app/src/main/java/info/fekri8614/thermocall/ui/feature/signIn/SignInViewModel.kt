package info.fekri8614.thermocall.ui.feature.signIn

import androidx.compose.runtime.mutableStateOf
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

class SignInViewModel(private val userRepository: UserRepository) : ViewModel() {
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    var uiState = mutableStateOf(SignInUiState())
        private set

    fun signInUser(auth: FirebaseAuth, data: SignInUiState, onUserAdded: (Task<AuthResult>) -> Unit) {
        userRepository.signInUser(
            auth = auth,
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