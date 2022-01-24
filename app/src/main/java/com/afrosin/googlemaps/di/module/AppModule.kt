package com.afrosin.googlemaps.di.module

import com.afrosin.googlemaps.ui.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App {
        return app
    }
}