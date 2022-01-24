package com.afrosin.googlemaps.mvp.presenter.adapter

import com.afrosin.googlemaps.mvp.model.MapMarker
import com.afrosin.googlemaps.mvp.view.item.MapMarkerItemView


interface MarkerRVListPresenter {
    fun getCount(): Int
    fun bind(viewBinding: MapMarkerItemView)
    fun setMapMarkerList(mapMarkerList: MutableList<MapMarker>)
    fun markerByPos(position: Int): MapMarker

}