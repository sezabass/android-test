package com.cesar.androidtest.httpclient.retrofit.di

import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface NetworkComponent {
    val retrofit: Retrofit
}