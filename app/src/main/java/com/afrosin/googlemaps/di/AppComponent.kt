package com.afrosin.googlemaps.di

import com.afrosin.googlemaps.di.module.AppModule
import com.afrosin.googlemaps.di.module.CiceroneModule
import com.afrosin.googlemaps.mvp.presenter.FragmentMapsPresenter
import com.afrosin.googlemaps.mvp.presenter.MainPresenter
import com.afrosin.googlemaps.mvp.presenter.MapMarkersPresenter
import com.afrosin.googlemaps.ui.activity.MainActivity
import com.afrosin.googlemaps.ui.adapter.MarkerRVAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(fragmentMapsPresenter: FragmentMapsPresenter)
    fun inject(markerRVAdapter: MarkerRVAdapter)
    fun inject(mapMarkersPresenter: MapMarkersPresenter)
}