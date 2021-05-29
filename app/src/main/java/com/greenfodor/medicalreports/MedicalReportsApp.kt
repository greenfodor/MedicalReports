package com.greenfodor.medicalreports

import android.app.Application
import com.greenfodor.medicalreports.di.appModule
import com.greenfodor.medicalreports.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MedicalReportsApp : Application() {
    companion object {
        lateinit var instance: MedicalReportsApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@MedicalReportsApp)
            androidLogger(Level.ERROR)

            modules(appModule + networkModule)
        }
    }
}