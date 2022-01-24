package com.afrosin.googlemaps.mvp.presenter

import com.afrosin.googlemaps.mvp.model.MapMarker
import com.afrosin.googlemaps.mvp.view.FragmentMapsView
import com.afrosin.googlemaps.navigation.Screens
import com.github.terrakok.cicerone.Router
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

    fun editMarkers(markers: ArrayList<MapMarker>) {
        router.navigateTo(Screens.onFragmentMapMarkersScreen(markers))
    }

}