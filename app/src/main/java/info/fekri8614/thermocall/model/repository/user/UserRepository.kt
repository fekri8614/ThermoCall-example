package info.fekri8614.thermocall.model.repository.user

interface UserRepository {
    fun getUserEmail(): String?
    fun saveUserEmail(email: String)

    fun getUserPassword(): String?
    fun saveUserPassword(pass: String)
}