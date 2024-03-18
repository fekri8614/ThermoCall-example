package info.fekri8614.thermocall.ui.feature.entry

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import info.fekri8614.thermocall.model.repository.user.UserRepository

class EntryViewModel(private val userRepository: UserRepository) : ViewModel() {
    val userEmail = MutableLiveData<String>("")
    val userPassword = MutableLiveData<String>("")

    fun setData(email: String, password: String) {
        userRepository.saveUserEmail(email)
        userRepository.saveUserPassword(password)
    }

    fun isUserDataSaved(): Boolean =
        !userRepository.getUserEmail().isNullOrEmpty() && !userRepository.getUserPassword().isNullOrEmpty()

}