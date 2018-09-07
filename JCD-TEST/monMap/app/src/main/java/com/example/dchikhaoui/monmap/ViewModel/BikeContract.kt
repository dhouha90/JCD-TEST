package com.example.dchikhaoui.monmap.ViewModel

import retrofit2.http.Body

interface BikeContract {
    fun recoverBikeList(@Body coordinates: String)
}