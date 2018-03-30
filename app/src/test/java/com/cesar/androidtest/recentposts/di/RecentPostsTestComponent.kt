package com.cesar.androidtest.recentposts.di

import com.cesar.androidtest.httpclient.retrofit.di.NetworkComponent
import dagger.Component

@RecentPostsScope
@Component(modules = arrayOf(RecentPostsTestModule::class),
        dependencies = arrayOf(NetworkComponent::class))
interface RecentPostsTestComponent {
    fun inject(recentPostsActivity: RecentPostsTestActivityMock)
}