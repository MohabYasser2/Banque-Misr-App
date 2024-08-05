package com.groupd.banquemisrapp.api

import UserAPICallable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserAPIService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://3.70.134.143")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userAPI: UserAPICallable =
        retrofit.create(UserAPICallable::class.java)
    }

