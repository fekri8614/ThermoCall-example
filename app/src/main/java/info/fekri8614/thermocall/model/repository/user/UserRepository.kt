package info.fekri8614.thermocall.model.repository.user

interface UserRepository {
    fun getUserName(): String?
    fun saveUserName(user: String)
}