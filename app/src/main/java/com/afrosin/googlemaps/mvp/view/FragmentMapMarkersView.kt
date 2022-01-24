package com.afrosin.googlemaps.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FragmentMapMarkersView : MvpView {

    fun init()
    fun updateMarkersAdapter()
}