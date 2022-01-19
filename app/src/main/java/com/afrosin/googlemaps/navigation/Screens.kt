package com.afrosin.googlemaps.navigation

import com.afrosin.googlemaps.ui.fragment.FragmentMapMarkers
import com.afrosin.googlemaps.ui.fragment.FragmentMaps
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.google.android.gms.maps.model.Marker

object Screens {
    fun onFragmentMapsScreen() = FragmentScreen { FragmentMaps.newInstance() }
    fun onFragmentMapMarkersScreen(markers: ArrayList<Marker>) = FragmentScreen { FragmentMapMarkers.newInstance(markers) }
}