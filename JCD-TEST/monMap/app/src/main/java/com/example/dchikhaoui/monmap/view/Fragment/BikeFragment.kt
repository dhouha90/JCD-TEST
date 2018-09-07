package com.example.dchikhaoui.monmap.view.Fragment

import android.location.Location
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dchikhaoui.monmap.R
import com.example.dchikhaoui.monmap.ViewModel.BikeViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class BikeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_maps, container, false)
    }

    private lateinit var mGoogleMap: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync { googleMap ->
            mGoogleMap = googleMap
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false)
            mGoogleMap.getUiSettings().setAllGesturesEnabled(true)
            mGoogleMap.getUiSettings().setMapToolbarEnabled(true)
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true)
            updateMap();
        }
    }

    fun createInstance(bundle: Bundle): BikeFragment {
        val fragment = BikeFragment()
        fragment.setArguments(bundle)
        return fragment
    }

    fun updateMap() {
        val latlng = LatLng(getArguments()?.getString("lat")!!.toDouble(), getArguments()?.getString("long")!!.toDouble())
        // val cameraPosition = CameraPosition.fromLatLngZoom(latlng, 15F)
        mGoogleMap.addMarker(MarkerOptions().position(latlng).title("Chikhaoui Dhouha"))
        BikeViewModel(mGoogleMap).recoverBikeList("moi");
        //mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10.0f))
    }
}