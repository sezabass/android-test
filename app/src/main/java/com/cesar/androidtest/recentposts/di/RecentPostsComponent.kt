package com.cesar.androidtest.recentposts.di

import com.cesar.androidtest.httpclient.retrofit.di.NetworkComponent
import com.cesar.androidtest.recentposts.RecentPostsActivity
import dagger.Component

@RecentPostsScope
@Component(modules = arrayOf(RecentPostsModule::class),
        dependencies = arrayOf(NetworkComponent::class))
interface RecentPostsComponent {
    fun inject(recentPostsActivity: RecentPostsActivity)
}