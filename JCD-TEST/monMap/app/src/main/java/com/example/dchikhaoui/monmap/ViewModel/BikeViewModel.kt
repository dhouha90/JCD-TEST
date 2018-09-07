package com.example.dchikhaoui.monmap.ViewModel

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class BikeViewModel : BikeContract {
    lateinit var mMap: GoogleMap

    constructor(mMap: GoogleMap) {
        this.mMap = mMap
    }


    override fun recoverBikeList(coordinates: String) {
        val sydney = LatLng(48.8976047, 2.3049632)
        mMap.addMarker(MarkerOptions()
                .position(sydney)
                .title("Chikhaoui Dhouha")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


        /* HttpResponse()
                 .getListVelo()
                 .subscribe(object : Observer<String> {
                     override fun onSubscribe(d: Disposable) {

                     }

                     override fun onNext(name: String) {
                         mMap.addMarker()

                     }

                     override fun onError(e: Throwable) {

                     }

                     override fun onComplete() {


                     }
                 }
                 );*/


    }
}