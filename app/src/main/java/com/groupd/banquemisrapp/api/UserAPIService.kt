package com.groupd.banquemisrapp.api

import UserAPICallable
import android.content.Context
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

object UserAPIService {

    private fun okhttpClient(token: String? = null) = OkHttpClient().newBuilder().addInterceptor(
        Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
            Log.d("TAG", "okhttpClient: $token")
            newRequest.addHeader("Authorization", "Bearer $token")
            chain.proceed(newRequest.build())
        })


    private val retrofit = Retrofit.Builder()
        .baseUrl("http://3.70.134.143")  // Update with your actual base URL
        .client(okhttpClient(token = TokenStorage.getToken(AppContextProvider.context)).build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userAPI: UserAPICallable = retrofit.create(UserAPICallable::class.java)


}




object AppContextProvider {
    lateinit var context: Context
}