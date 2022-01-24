package com.afrosin.googlemaps.mvp.presenter

import com.afrosin.googlemaps.mvp.model.MapMarker
import com.afrosin.googlemaps.mvp.presenter.adapter.MarkerRVListPresenter
import com.afrosin.googlemaps.mvp.view.FragmentMapMarkersView
import com.afrosin.googlemaps.mvp.view.item.MapMarkerItemView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MapMarkersPresenter(val markerList: ArrayList<MapMarker>) :
    MvpPresenter<FragmentMapMarkersView>() {

    @Inject
    lateinit var router: Router


    val listPresenter = MarkerDetailPresenter()

    inner class MarkerDetailPresenter : MarkerRVListPresenter {

        private val mapMarkerList = mutableListOf<MapMarker>()
        override fun getCount(): Int = mapMarkerList.size

        override fun setMapMarkerList(mapMarkerList: MutableList<MapMarker>) {
            if (this.mapMarkerList.count() > 0) {
                this.mapMarkerList.clear()
            }
            this.mapMarkerList.addAll(mapMarkerList)
        }

        override fun bind(view: MapMarkerItemView) {
            initView(view, markerByPos(view.pos))
        }

        override fun markerByPos(position: Int): MapMarker = mapMarkerList[position]

        private fun initView(view: MapMarkerItemView, marker: MapMarker) {
            with(view) {

                marker.title?.let { title -> setMapLabelNameText(title) }

            }
        }


    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        listPresenter.setMapMarkerList(markerList)
        viewState.updateMarkersAdapter()
    }


}