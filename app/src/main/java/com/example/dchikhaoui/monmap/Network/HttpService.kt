package com.example.dchikhaoui.monmap.Network


import com.example.dchikhaoui.monmap.BuildConfig
import com.example.dchikhaoui.monmap.Utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class HttpService @Inject constructor() {

    @Provides
    @Singleton
    fun getClient(): HttpInterface {
        val httpClient = OkHttpClient
                .Builder()
                .addInterceptor(interceptor())

        val builder = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BIKE_WS_DOMAIN)

        val retrofit = builder
                .client(httpClient.build())
                .build()

        return retrofit.create(HttpInterface::class.java);
    }

    inner class interceptor() :
            Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()

            val url = request.url().newBuilder()
                    .addQueryParameter(Constants.API_KEY, BuildConfig.API_KEY)
                    .build()

            val newRequest = request.newBuilder()
                    .url(url)
                    .build()

            return chain.proceed(newRequest)
        }
    }

}
