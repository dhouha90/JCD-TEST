package com.example.dchikhaoui.monmap.Network

import com.example.dchikhaoui.monmap.Model.Bike
import io.reactivex.Observable
import retrofit2.http.*

interface HttpInterface {
    @GET("stations")
    fun getListVelo(): Observable<List<Bike>>

    @GET("stations/{ContratId}")
    fun detaillBike(@Path("ContratId") contractId: Int, @Query("contract") contract: String): Observable<Bike>
}