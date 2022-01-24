package com.afrosin.googlemaps.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.afrosin.googlemaps.databinding.FragmentMapMarkersBinding
import com.afrosin.googlemaps.mvp.model.MapMarker
import com.afrosin.googlemaps.mvp.presenter.MapMarkersPresenter
import com.afrosin.googlemaps.mvp.view.FragmentMapMarkersView
import com.afrosin.googlemaps.ui.App
import com.afrosin.googlemaps.ui.adapter.MarkerRVAdapter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class FragmentMapMarkers() : MvpAppCompatFragment(),
    FragmentMapMarkersView {


    private lateinit var _binding: FragmentMapMarkersBinding
    private val binding get() = _binding

    @InjectPresenter
    lateinit var presenter: MapMarkersPresenter

    @ProvidePresenter
    fun providePresenter() =
        MapMarkersPresenter(requireArguments()[MARKERS_KEY] as ArrayList<MapMarker>).apply {
            App.instance.appComponent.inject(this)
        }

    private var mapMarkersRVAdapter: MarkerRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapMarkersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mapMarkersRVAdapter = MarkerRVAdapter(presenter.listPresenter)
        binding.rvMapMarkers.run {
            adapter = mapMarkersRVAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun updateMarkersAdapter() {
        mapMarkersRVAdapter?.notifyDataSetChanged()
    }

    companion object {
        const val MARKERS_KEY = "MARKERS_KEY"
        fun newInstance(markers: ArrayList<MapMarker>) = FragmentMapMarkers().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(MARKERS_KEY, markers)
            }
        }
    }
}