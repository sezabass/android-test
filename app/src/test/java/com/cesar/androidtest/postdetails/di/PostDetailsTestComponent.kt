package com.cesar.androidtest.postdetails.di

import com.cesar.androidtest.httpclient.retrofit.di.NetworkComponent
import dagger.Component

@PostDetailsScope
@Component(modules = arrayOf(PostDetailsTestModule::class),
        dependencies = arrayOf(NetworkComponent::class))
interface PostDetailsTestComponent {
    fun inject(postDetailsActivity: PostDetailsTestActivityMock)
}