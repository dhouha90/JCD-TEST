package com.example.dchikhaoui.monmap.Network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class HttpService {

        fun getClient(): HttpInterface {
            val httpClient = OkHttpClient.Builder()

            val builder = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("your_server_url")

            val retrofit = builder
                    .client(httpClient.build())
                    .build()

            return retrofit.create(HttpInterface::class.java);
        }

}