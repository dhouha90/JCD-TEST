package com.example.dchikhaoui.monmap.Network

import com.example.dchikhaoui.monmap.Model.Bike



import io.reactivex.Observable
import javax.inject.Inject


class HttpResponse @Inject constructor() {

    @Inject
    lateinit var httpResponse: HttpService


    fun getInterface(): HttpInterface {
        DaggerNetworkComponent.builder().build().inject(this)
        return httpResponse.getClient()
    }

    fun getListVelo(): Observable<List<Bike>> {

        return getInterface().getListVelo()

    }

    fun detailBike(contractId: Int, contractName: String): Observable<Bike> {
        return getInterface().detaillBike(contractId, contractName);
    }
}