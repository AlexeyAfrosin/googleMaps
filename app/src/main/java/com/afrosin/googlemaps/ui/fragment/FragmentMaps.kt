package com.afrosin.googlemaps.ui.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.afrosin.googlemaps.R
import com.afrosin.googlemaps.databinding.FragmentMapsBinding
import com.afrosin.googlemaps.mvp.model.MapMarker
import com.afrosin.googlemaps.mvp.presenter.FragmentMapsPresenter
import com.afrosin.googlemaps.mvp.view.FragmentMapsView
import com.afrosin.googlemaps.ui.App
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.io.IOException

class FragmentMaps : MvpAppCompatFragment(), FragmentMapsView {

    private lateinit var map: GoogleMap

    private lateinit var _binding: FragmentMapsBinding
    private val binding get() = _binding

    private val markers: ArrayList<MapMarker> = arrayListOf()

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {}

    @InjectPresenter
    lateinit var presenter: FragmentMapsPresenter

    @ProvidePresenter
    fun providePresenter() = FragmentMapsPresenter().apply {
        App.instance.appComponent.inject(this)
    }


    private val callback = OnMapReadyCallback { googleMap ->

        map = googleMap
        val initialPlace = LatLng(52.52000659999999, 13.404953999999975)
        googleMap.addMarker(
            MarkerOptions().position(initialPlace).title(getString(R.string.marker_start))
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(initialPlace))
        googleMap.setOnMapLongClickListener { latLng ->
            getAddressAsync(latLng)
            addMarkerToArray(latLng)
        }
        activateMyLocation(googleMap)

    }

    override fun init() {
        initListeners()
    }

    private fun initListeners() {
        initSearchByAddress()
        binding.btEditLabelList.setOnClickListener {
            presenter.editMarkers(markers)
        }
    }

    private fun activateMyLocation(googleMap: GoogleMap) {
        context?.let { context ->
            val isPermissionGradient = (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)

            if (isPermissionGradient) {
                googleMap.isMyLocationEnabled = isPermissionGradient
                googleMap.uiSettings.isMyLocationButtonEnabled = isPermissionGradient
            } else {
                requestPermission.launch(
                    arrayOf(
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                )
                activateMyLocation(googleMap)
            }

        }
    }

    private fun addMarkerToArray(location: LatLng) {
        val marker =
            setMarker(location, markers.size.toString(), R.drawable.ic_map_pin)
        markers.add(MapMarker(marker.title, marker.position))
    }

    private fun setMarker(location: LatLng, title: String, resourceId: Int): Marker {
        return map.addMarker(
            MarkerOptions()
                .position(location)
                .title(title)
                .icon(BitmapDescriptorFactory.fromResource(resourceId))
        )!!

    }

    private fun getAddressAsync(location: LatLng) {
        context?.let {
            val geoCoder = Geocoder(it)
            Thread {
                try {
                    val address = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
                    binding.textAddress.post {
                        binding.textAddress.text = address[0].getAddressLine(0)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }.start()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        if (binding.searchAddress.text != null) {
            binding.buttonSearch.callOnClick()
        }
    }


    private fun initSearchByAddress() {
        binding.buttonSearch.setOnClickListener {
            val geoCoder = Geocoder(it.context)
            val searchText = binding.searchAddress.text.toString()
            Thread {
                try {
                    val addresses = geoCoder.getFromLocationName(searchText, 1)
                    if (addresses.size > 0) {
                        goToAddress(addresses, it, searchText)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }.start()
        }


    }

    private fun goToAddress(addresses: MutableList<Address>, view: View, searchText: String) {
        val location = LatLng(addresses[0].latitude, addresses[0].longitude)
        view.post {
            setMarker(location, searchText, R.drawable.ic_map_marker)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
        }
    }

    companion object {
        fun newInstance() = FragmentMaps()
    }

}