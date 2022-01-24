package com.afrosin.googlemaps.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afrosin.googlemaps.databinding.ItemMapMarkerBinding
import com.afrosin.googlemaps.mvp.presenter.adapter.MarkerRVListPresenter
import com.afrosin.googlemaps.mvp.view.item.MapMarkerItemView
import com.afrosin.googlemaps.ui.App
import com.github.terrakok.cicerone.Router
import kotlinx.android.synthetic.main.item_map_marker.view.*
import javax.inject.Inject

class MarkerRVAdapter(val presenter: MarkerRVListPresenter) :
    RecyclerView.Adapter<MarkerRVAdapter.MarkerViewHolder>() {

    @Inject
    lateinit var router: Router


    init {
        App.instance.appComponent.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkerViewHolder {
        val binding = ItemMapMarkerBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MarkerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarkerViewHolder, position: Int) {
        holder.pos = position
        presenter.bind(holder)
    }

    override fun getItemCount(): Int = presenter.getCount()

    inner class MarkerViewHolder(binding: ItemMapMarkerBinding) :
        RecyclerView.ViewHolder(binding.root),
        MapMarkerItemView {

        override var pos: Int = -1

        override fun setMapLabelNameText(text: String) {
            itemView.ed_label_name.setText(text)
        }
    }


}