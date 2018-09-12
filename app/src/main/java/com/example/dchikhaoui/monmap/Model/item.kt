package com.example.dchikhaoui.monmap.Model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class item(
        private val title: String,
        private val latLing: LatLng,
        private val snippset: String,
        private val bike: Bike
) : ClusterItem {
    override fun getSnippet(): String {
        return snippset
    }

    override fun getTitle(): String {
        return title
    }

    override fun getPosition(): LatLng {
        return latLing
    }

    fun getBike(): Bike {
        return bike
    }
}