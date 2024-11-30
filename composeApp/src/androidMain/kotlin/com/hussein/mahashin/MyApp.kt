package com.hussein.mahashin

import com.hussein.mahashin.di.KoinInitializer
import android.app.Application

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}