package com.example.gorko

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class GorkoApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        // Инициализация Timber для логирования
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        
        // Здесь можно добавить инициализацию других библиотек
        // Например: Firebase, Crashlytics, Analytics и т.д.
    }
}