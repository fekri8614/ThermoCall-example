package info.fekri8614.thermocall.model.repository.user

import android.content.SharedPreferences
import info.fekri8614.thermocall.util.KEY_USER_EMAIL
import info.fekri8614.thermocall.util.KEY_USER_PASSWORD

class UserRepositoryImpl(private val sharedPreferences:SharedPreferences): UserRepository {
    override fun getUserEmail(): String? = sharedPreferences.getString(KEY_USER_EMAIL, null)

    override fun saveUserEmail(email: String) {
        sharedPreferences.edit().apply {
            putString(KEY_USER_EMAIL, email)
        }.apply()
    }

    override fun getUserPassword(): String? {
        return sharedPreferences.getString(KEY_USER_PASSWORD, null)
    }

    override fun saveUserPassword(pass: String) {
        sharedPreferences.edit().apply {
            putString(KEY_USER_PASSWORD, pass)
        }.apply()
    }
}