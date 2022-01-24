package com.afrosin.googlemaps.mvp.model

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MapMarker(
    val title: String?,
    val position: LatLng
) : Parcelable
