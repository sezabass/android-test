package com.cesar.androidtest.postdetails.di

import com.cesar.androidtest.httpclient.retrofit.di.NetworkComponent
import com.cesar.androidtest.postdetails.PostDetailsActivity
import dagger.Component

@PostDetailsScope
@Component(modules = [(PostDetailsModule::class)],
        dependencies = [(NetworkComponent::class)])
interface PostDetailsComponent {
    fun inject(postDetailsActivity: PostDetailsActivity)
}