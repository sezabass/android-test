package com.cesar.androidtest.postdetails.di

import com.cesar.androidtest.httpclient.retrofit.di.DaggerNetworkComponent
import com.cesar.androidtest.postdetails.PostDetailsActivity

class PostDetailsTestActivityMock : PostDetailsActivity() {

    override fun initializeDependencies() {
        DaggerPostDetailsTestComponent.builder()
                .postDetailsTestModule(PostDetailsTestModule(this))
                .networkComponent(DaggerNetworkComponent.create())
                .build()
                .inject(this)
    }
}