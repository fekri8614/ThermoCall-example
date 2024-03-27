package info.fekri8614.thermocall.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import info.fekri8614.thermocall.model.net.createApiService
import info.fekri8614.thermocall.model.repository.thermocall.ThermoCallRepository
import info.fekri8614.thermocall.model.repository.thermocall.ThermoCallRepositoryImpl
import info.fekri8614.thermocall.model.repository.user.UserRepository
import info.fekri8614.thermocall.model.repository.user.UserRepositoryImpl
import info.fekri8614.thermocall.ui.feature.dashboard.DashboardViewModel
import info.fekri8614.thermocall.ui.feature.signUp.SignUpViewModel
import info.fekri8614.thermocall.ui.feature.signIn.SignInViewModel
import info.fekri8614.thermocall.ui.feature.splashScreen.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module {
    single { androidContext().getSharedPreferences("data_sh_book", Context.MODE_PRIVATE) }
    single { createApiService() }

    single<UserRepository> { UserRepositoryImpl(get()) }
    single<ThermoCallRepository> { ThermoCallRepositoryImpl(get()) }
    single<FirebaseAuth> { FirebaseAuth.getInstance() }

    viewModel { SignInViewModel(get(), get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { DashboardViewModel(get()) }
    viewModel { SplashViewModel(get()) }

}
