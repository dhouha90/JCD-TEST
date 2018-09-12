package com.example.dchikhaoui.monmap.view.Fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dchikhaoui.monmap.Model.item
import com.example.dchikhaoui.monmap.R
import com.example.dchikhaoui.monmap.Utils.Constants
import com.example.dchikhaoui.monmap.ViewModel.BikeViewModel
import com.example.dchikhaoui.monmap.view.Activity.DetailBikeActivity
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import javax.inject.Singleton


class BikeMapFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    private lateinit var mGoogleMap: GoogleMap
    lateinit var mClusterManager: ClusterManager<item>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync { googleMap ->

            mGoogleMap = googleMap
            mClusterManager = ClusterManager<item>(context, googleMap)
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false)
            mGoogleMap.getUiSettings().setAllGesturesEnabled(true)
            mGoogleMap.getUiSettings().setMapToolbarEnabled(true)
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true)
            mGoogleMap.setOnCameraIdleListener(mClusterManager)
            mGoogleMap.setOnMarkerClickListener(mClusterManager)
            mGoogleMap.setOnInfoWindowClickListener(mClusterManager)

            mClusterManager.setOnClusterItemClickListener(
                    ClusterManager.OnClusterItemClickListener<item> {
                        var mBundle = Bundle()
                        mBundle.putString(Constants.CONTRACT_NAME, it.getBike().contract_name)
                        mBundle.putInt(Constants.STATION_ID, it.getBike().number)
                        val intent = Intent(context, DetailBikeActivity::class.java)
                        intent.putExtras(mBundle)
                        startActivity(intent)
                        false
                    })

            initMap(this.context!!);
        }

    }


    @Singleton
    fun createInstance(): BikeMapFragment {
        val fragment = BikeMapFragment()
        return fragment
    }

    @SuppressLint("MissingPermission")
    fun initMap(mContext: Context) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext)
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location ->
            addMaker(LatLng(location.latitude, location.longitude))
        }
    }


    fun addMaker(mLatlng: LatLng) {
        mGoogleMap.addMarker(MarkerOptions().position(mLatlng).title(R.string.marker_title.toString()))
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatlng, 9f))
        BikeViewModel(mGoogleMap, mClusterManager, this.context!!).getBikeList()
    }


}