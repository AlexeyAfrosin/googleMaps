package com.afrosin.googlemaps.navigation

import com.afrosin.googlemaps.mvp.model.MapMarker
import com.afrosin.googlemaps.ui.fragment.FragmentMapMarkers
import com.afrosin.googlemaps.ui.fragment.FragmentMaps
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun onFragmentMapsScreen() = FragmentScreen { FragmentMaps.newInstance() }
    fun onFragmentMapMarkersScreen(markers: ArrayList<MapMarker>) =
        FragmentScreen { FragmentMapMarkers.newInstance(markers) }
}