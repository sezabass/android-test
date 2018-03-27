package com.cesar.androidtest.httpclient.retrofit.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Suppress("unused")
@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
    }
}