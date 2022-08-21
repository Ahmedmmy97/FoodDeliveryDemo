package com.example.taskfood.NetworkLayer

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object ApiService {
    private val TAG = "--ApiService"

    fun ApiCall() = Retrofit.Builder()
        .baseUrl(FoodApi.BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ApiWorker.gsonConverter)
        .client(ApiWorker.client)
        .build()
        .create(FoodApi::class.java)
}