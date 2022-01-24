package com.afrosin.googlemaps.ui

import android.app.Application
import com.afrosin.googlemaps.di.AppComponent
import com.afrosin.googlemaps.di.DaggerAppComponent
import com.afrosin.googlemaps.di.module.AppModule

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}