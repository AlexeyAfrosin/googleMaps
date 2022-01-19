package com.afrosin.googlemaps.mvp.presenter

import com.afrosin.googlemaps.mvp.view.FragmentMapsView
import com.afrosin.googlemaps.navigation.Screens
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.google.android.gms.maps.model.Marker
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class FragmentMapsPresenter : MvpPresenter<FragmentMapsView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.getMap()
    }

    fun editMarkers(markers: ArrayList<Marker>) {
        router.navigateTo(Screens.onFragmentMapMarkersScreen(markers))
    }

}