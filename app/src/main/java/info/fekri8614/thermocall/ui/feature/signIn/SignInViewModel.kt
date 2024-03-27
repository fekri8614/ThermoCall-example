package info.fekri8614.thermocall.ui.feature.signIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import info.fekri8614.thermocall.model.data.SignInUiState
import info.fekri8614.thermocall.model.repository.user.UserRepository

class SignInViewModel(private val userRepository: UserRepository, private val firebaseAuth: FirebaseAuth) : ViewModel() {
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    fun signInUser(data: SignInUiState, onUserAdded: (Task<AuthResult>) -> Unit) {
        userRepository.signInUser(
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