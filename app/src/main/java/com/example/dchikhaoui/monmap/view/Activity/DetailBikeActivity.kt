package com.example.dchikhaoui.monmap.view.Activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.dchikhaoui.monmap.R
import com.example.dchikhaoui.monmap.Utils.Constants
import com.example.dchikhaoui.monmap.ViewModel.BikeViewModel
import com.example.dchikhaoui.monmap.databinding.ActivityDetailBikeBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment

class DetailBikeActivity : AppCompatActivity() {
    private lateinit var mGoogleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        val mName = intent.extras.getString(Constants.CONTRACT_NAME)
        val mId = intent.extras.getInt(Constants.STATION_ID)
        var mActivityDetailBikeBinding: ActivityDetailBikeBinding = DataBindingUtil.setContentView(this, com.example.dchikhaoui.monmap.R.layout.activity_detail_bike)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync { googleMap ->
            mGoogleMap = googleMap
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false)
            mGoogleMap.getUiSettings().setAllGesturesEnabled(false)
            mGoogleMap.getUiSettings().setMapToolbarEnabled(false)
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false)
            BikeViewModel(mGoogleMap).detaileBike(mName, mId, mActivityDetailBikeBinding);
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            (android.R.id.home) -> {
                onBackPressed();
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}