plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "info.fekri8614.thermocall"
    compileSdk = 34

    defaultConfig {
        applicationId = "info.fekri8614.thermocall"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    val composeUiVersion = "1.4.0"

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("androidx.compose.ui:ui:$composeUiVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeUiVersion")
    implementation("androidx.compose.material:material:1.4.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeUiVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeUiVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeUiVersion")

    // System Ui Controller
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.17.0")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha01")

    // LiveData-State
    implementation("androidx.compose.runtime:runtime-livedata:$composeUiVersion")

    // Coil
    implementation("io.coil-kt:coil-compose:2.3.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    // Koin
    implementation("dev.burnoo:cokoin:1.0.0")
    implementation("dev.burnoo:cokoin-android-viewmodel:1.0.0")
    implementation("dev.burnoo:cokoin-android-navigation:1.0.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Lottie
    implementation("com.airbnb.android:lottie-compose:5.2.0")

    // collapsing toolbar
    implementation("me.onebone:toolbar-compose:2.3.5")

    // Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.7.4"))

    // Firebase Messaging
    implementation("com.google.firebase:firebase-messaging")

    // Firebase Authentication
    implementation("com.google.firebase:firebase-auth-ktx")

}