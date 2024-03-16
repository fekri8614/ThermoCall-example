package info.fekri8614.thermocall.model.repository.user

import android.content.SharedPreferences
import info.fekri8614.thermocall.util.KEY_USER_NAME

class UserRepositoryImpl(private val sharedPreferences:SharedPreferences): UserRepository {
    override fun getUserName(): String? = sharedPreferences.getString(KEY_USER_NAME, null)

    override fun saveUserName(user: String) {
        sharedPreferences.edit().apply {
            putString(KEY_USER_NAME, user)
        }.apply()
    }
}