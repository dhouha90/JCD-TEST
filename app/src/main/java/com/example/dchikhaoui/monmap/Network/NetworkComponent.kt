package com.example.dchikhaoui.monmap.Network

import com.example.dchikhaoui.monmap.Network.HttpResponse
import com.example.dchikhaoui.monmap.Network.HttpService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HttpService::class])
interface NetworkComponent {
    fun inject(serviceResponse: HttpResponse)
}