package com.example.dchikhaoui.monmap.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class Bike(val number: Int,
                val contract_name: String,
                val name: String,
                val address: String,
                val position: Position,
                val banking: Boolean,
                val bonus: Boolean,
                val bike_stands: Int,
                val available_bike_stands: Int,
                val available_bikes: Int,
                val status: String,
                val last_update: Long)  {


    override fun toString(): String {
        return "Bike(number=$number, contract_name='$contract_name', name='$name', address='$address', banking=$banking, bonus=$bonus, bike_stands=$bike_stands, available_bike_stands=$available_bike_stands, available_bikes=$available_bikes, status='$status', last_update=$last_update)"
    }

     class Position(val lat: Double,
                         val lng: Double) {

        override fun toString(): String {
            return "position(lat='$lat', lng='$lng')"
        }
    }


}