plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.compiler.ksp)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.gorko" // Измените на ваш пакет
    compileSdk = 34 // Или выше, в зависимости от требований Material3 и других библиотек

    defaultConfig {
        applicationId = "com.example.gorko" // Измените на ваш applicationId
        minSdk = 28 // Указано в ТЗ: Android 9 (API 28)
        targetSdk = 34 // Или выше
        versionCode = 1
        versionName = "0.1" // Соответствует версии из ТЗ

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Добавляем поддержку русской и английской локализации
        resourceConfigurations += listOf("ru", "en")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // Certificate Pinning для защиты от MITM (согласно ТЗ)
            buildConfigField("String", "API_BASE_URL", "\"https://api.gorko.app/api/v1/\"")
            buildConfigField("String", "CERTIFICATE_PIN", "\"sha256/...\"") // Добавить актуальный пин сертификата
        }

        debug {
            isMinifyEnabled = false
            isDebuggable = true
            buildConfigField("String", "API_BASE_URL", "\"https://dev-api.gorko.app/api/v1/\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "2.1.10-1.0.30" // Должно соответствовать версии Kotlin и Compose
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // WorkManager
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.hilt.work)
    ksp(libs.androidx.hilt.compiler)

    // Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.preference.ktx)

    // Compose UI
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.ui.text)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.foundation)
    implementation(libs.swipe)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Lifecycle & ViewModel
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Database - Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // SQLCipher для шифрования (согласно ТЗ)
    implementation(libs.sqlcipher)
    implementation(libs.sqlite.ktx)

    // Dependency Injection - Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    // Asynchronous - Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Networking - Retrofit & OkHttp (согласно ТЗ)
    implementation(libs.retrofit.core)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.converter.gson)

    // Data Storage
    implementation(libs.datastore.preferences)

    // Data Parsing & Serialization
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.opencsv)
    implementation(libs.poi)
    implementation(libs.poi.ooxml)

    // UI Components
    implementation(libs.compose)  // Calendar
    implementation(libs.mpandroidchart)  // Charts (согласно ТЗ для DonutChart)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.lottie.compose)  // Для анимаций (согласно ТЗ)
    implementation(libs.coil.compose)  // Image loading
    implementation(libs.androidx.animation)

    // Logging
    implementation(libs.timber)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.room.testing)
    androidTestImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    testImplementation(libs.mockito.kotlin)
    implementation(libs.accompanist.systemuicontroller)
    testImplementation(kotlin("test"))
    androidTestImplementation(libs.androidx.core)
    androidTestImplementation(libs.androidx.runner)
}