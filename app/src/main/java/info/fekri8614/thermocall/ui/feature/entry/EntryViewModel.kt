package info.fekri8614.thermocall.ui.feature.entry

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import info.fekri8614.thermocall.model.repository.user.UserRepository

class EntryViewModel(private val userRepository: UserRepository): ViewModel() {
    val fullName = MutableLiveData("")

    fun setData(userName:String) {
        userRepository.saveUserName(userName)
    }

    fun isUserDataSaved(): Boolean = !userRepository.getUserName().isNullOrEmpty()
}