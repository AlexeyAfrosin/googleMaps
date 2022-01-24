package com.afrosin.googlemaps.mvp.presenter

import com.afrosin.googlemaps.mvp.view.MainView
import com.afrosin.googlemaps.navigation.Screens
import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.onFragmentMapsScreen())
    }
}