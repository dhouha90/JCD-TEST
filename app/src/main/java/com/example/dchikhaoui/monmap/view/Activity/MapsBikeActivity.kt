package com.example.dchikhaoui.monmap.view.Activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.dchikhaoui.monmap.R
import com.example.dchikhaoui.monmap.Utils.Constants
import com.example.dchikhaoui.monmap.view.Fragment.BikeMapFragment
import kotlinx.android.synthetic.main.activity_bike.*

@SuppressLint("ByteOrderMark")
class MapsBikeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), Constants.PERMISSION_CODE)
            return
        }
        initView()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Constants.PERMISSION_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ressayer.visibility = View.GONE
                    initView()
                } else {
                    ressayer.visibility = View.VISIBLE
                }
            }
        }
    }

    fun initView() {
        val fragment = BikeMapFragment().createInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, fragment, fragment::class.java.name)
                .addToBackStack(fragment::class.java.name)
                .commitAllowingStateLoss()
    }

    fun tryAgain(view: View) {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), Constants.PERMISSION_CODE)

    }

}
