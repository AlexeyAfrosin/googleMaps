package com.afrosin.googlemaps.mvp.view

import com.google.android.gms.maps.model.Marker
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FragmentMapsView : MvpView {
    fun init()
    fun getMap()
}