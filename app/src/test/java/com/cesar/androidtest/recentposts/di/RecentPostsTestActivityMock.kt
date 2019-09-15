package com.cesar.androidtest.recentposts.di

import com.cesar.androidtest.httpclient.retrofit.di.DaggerNetworkComponent
import com.cesar.androidtest.recentposts.presentation.RecentPostsActivity

class RecentPostsTestActivityMock : RecentPostsActivity() {

    override fun initializeDependencies() {
        DaggerRecentPostsTestComponent.builder()
                .recentPostsTestModule(RecentPostsTestModule(this))
                .networkComponent(DaggerNetworkComponent.create())
                .build()
                .inject(this)
    }
}