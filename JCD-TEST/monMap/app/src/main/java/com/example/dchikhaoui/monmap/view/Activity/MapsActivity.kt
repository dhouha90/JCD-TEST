package com.example.dchikhaoui.monmap.view.Activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.dchikhaoui.monmap.R
import com.example.dchikhaoui.monmap.view.Fragment.BikeFragment
import com.example.dchikhaoui.monmap.view.Fragment.BikeListFragment
import com.google.android.gms.location.LocationServices

@SuppressLint("ByteOrderMark")
class MapsActivity : AppCompatActivity() {
    lateinit var mMapMenuItem: MenuItem
    lateinit var mListMenuItem: MenuItem
    lateinit var mLocation: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                mLocation = location!!
                //  val sydney = LatLng(location!!.latitude, location.longitude)
                val bundle = Bundle()
                bundle.putString("lat", location.latitude.toString())
                bundle.putString("long", location.longitude.toString())
                val fragment = BikeFragment().createInstance(bundle)
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment, fragment, fragment::class.java.name)
                        .addToBackStack(fragment::class.java.name)
                        .commit()
            }
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 123)
            // Show rationale and request permission.
        }

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            123 -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Log.i("la", "Permission has been denied by user")
                } else {
                    Log.i("la", "Permission has been granted by user")
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        mMapMenuItem = menu.findItem(R.id.map_switch)
        mListMenuItem = menu.findItem(R.id.list_switch)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            (R.id.list_switch) -> {
                mMapMenuItem.setVisible(true)
                mListMenuItem.setVisible(false)
                val bundle = Bundle()
                val fragment = BikeListFragment().createInstance(bundle)
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment, fragment, fragment::class.java.name)
                        .addToBackStack(fragment::class.java.name)
                        .commit()

                return true
            }

            (R.id.map_switch) -> {
                mMapMenuItem.setVisible(false)
                mListMenuItem.setVisible(true)
                val bundle = Bundle()
                bundle.putString("lat", mLocation.latitude.toString())
                bundle.putString("long", mLocation.longitude.toString())
                val fragment = BikeFragment().createInstance(bundle)
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment, fragment, fragment::class.java.name)
                        .addToBackStack(fragment::class.java.name)
                        .commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

