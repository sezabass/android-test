package com.cesar.androidtest.httpclient.retrofit

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
}
