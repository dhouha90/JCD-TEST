package com.example.dchikhaoui.monmap.ViewModel

import android.content.Context
import android.util.Log
import com.example.dchikhaoui.monmap.Model.Bike
import com.example.dchikhaoui.monmap.Model.item
import com.example.dchikhaoui.monmap.Network.HttpResponse
import com.example.dchikhaoui.monmap.databinding.ActivityDetailBikeBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers

class BikeViewModel : BikeContract {
    var TAG: String = BikeViewModel::class.java.getSimpleName()

    var mMap: GoogleMap
    lateinit var mClusterManager: ClusterManager<item>
    lateinit var mContext: Context

    constructor(mMap: GoogleMap, mClusterManager: ClusterManager<item>, mContext: Context) {
        this.mMap = mMap
        this.mClusterManager = mClusterManager
        this.mContext = mContext
    }

    constructor(mMap: GoogleMap) {
        this.mMap = mMap
    }


    override fun getBikeList() {
        HttpResponse()
                .getListVelo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMapIterable { items -> items }
                .filter(Predicate { t ->  t.status.equals(com.example.dchikhaoui.monmap.Utils.Constants.STATUT) })
                .subscribe(object : Observer<Bike> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(mBike: Bike) {
                        var mLatLing = LatLng(mBike.position.lat, mBike.position.lng)
                        mClusterManager.addItem(item(mBike.name, mLatLing, mBike.status, mBike))
                    }

                    override fun onError(e: Throwable) {
                        Log.i(TAG, e.toString())
                    }

                    override fun onComplete() {
                        mClusterManager.cluster();
                    }
                })

    }


    override fun detaileBike(mNameContract: String, mBikeId: Int, mActivityDetailBikeBinding: ActivityDetailBikeBinding) {
        HttpResponse().detailBike(mBikeId, mNameContract)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    val latlng = LatLng(it.position.lat, it.position.lng)
                    mMap.addMarker(MarkerOptions().position(latlng).title(it.name))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 12.0f))
                    mActivityDetailBikeBinding.bike = it
                }, {
                    Log.i(TAG, it.toString())
                })


    }


}