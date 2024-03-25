package info.fekri8614.thermocall.model.repository.user

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

interface UserRepository {
    fun getUserEmail(): String?
    fun saveUserEmail(email: String)

    fun getUserPassword(): String?
    fun saveUserPassword(pass: String)

    fun signInUser(auth: FirebaseAuth, email: String, pass: String, onUserAdded: (Task<AuthResult>) -> Unit)
    fun signUpUser(auth: FirebaseAuth, email: String, pass: String, onUserAdded: (Task<AuthResult>) -> Unit)
}