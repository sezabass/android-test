package com.cesar.androidtest.httpclient.retrofit

import com.cesar.androidtest.recentposts.model.RecentPostsService
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class RetrofitInitializerTest {

    private lateinit var retrofitInitializer: RetrofitInitializer

    @Before
    fun setUp() {
        retrofitInitializer = RetrofitInitializer()
    }

    @Test
    fun whenRecentPostsServiceThenReturnNonNullObject() {
        val service: RecentPostsService = retrofitInitializer.recentPostsService()
        assertNotNull(service)
    }

}