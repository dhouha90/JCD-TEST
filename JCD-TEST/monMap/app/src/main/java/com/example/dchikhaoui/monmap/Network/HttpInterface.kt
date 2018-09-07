package com.example.dchikhaoui.monmap.Network

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface HttpInterface {
    @POST("/location")
    fun getListVelo(@Body coordinates: String): Observable<String>


}