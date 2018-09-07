package com.example.dchikhaoui.monmap.Network

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class HttpResponse {
    fun getListVelo() : Observable<String> {
     return HttpService().getClient()
             .getListVelo("test")
             .subscribeOn(Schedulers.io());
    }
}