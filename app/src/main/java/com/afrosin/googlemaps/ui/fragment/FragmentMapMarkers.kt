package com.afrosin.googlemaps.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afrosin.googlemaps.databinding.FragmentMapMarkersBinding
import com.afrosin.googlemaps.mvp.view.FragmentMapMarkersView
import com.google.android.gms.maps.model.Marker
import moxy.MvpAppCompatFragment

class FragmentMapMarkers(var markers: ArrayList<Marker>) : MvpAppCompatFragment(),
    FragmentMapMarkersView {


    private lateinit var _binding: FragmentMapMarkersBinding
    private val binding get() = _binding

    private var mapMarkersRVAdapter: CommentRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapMarkersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
//        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val markers = arguments?.getParcelable<ArrayList<Marker>>(MARKERS)
    }


    companion object {
        const val MARKERS = "MARKERS"
        fun newInstance(markers: ArrayList<Marker>) = FragmentMapMarkers(markers)
    }
}