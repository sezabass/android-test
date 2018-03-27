package com.cesar.androidtest.recentposts.di

import com.cesar.androidtest.recentposts.RecentPostsActivity
import com.cesar.androidtest.recentposts.RecentPostsModel
import com.cesar.androidtest.recentposts.RecentPostsPresenter
import com.cesar.androidtest.recentposts.model.RecentPostsApi
import com.cesar.androidtest.recentposts.model.RecentPostsApiImpl
import com.cesar.androidtest.recentposts.model.RecentPostsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RecentPostsModule(val activity: RecentPostsActivity){

    @RecentPostsScope
    @Provides
    fun recentPostsService(retrofit: Retrofit): RecentPostsService {
        return retrofit.create(RecentPostsService::class.java)
    }

    @RecentPostsScope
    @Provides
    fun RecentPostsApi(service: RecentPostsService): RecentPostsApi {
        return RecentPostsApiImpl(service)
    }

    @RecentPostsScope
    @Provides
    fun recentPostsPresenter(api: RecentPostsApi): RecentPostsPresenter {
        val model = RecentPostsModel(api)
        val presenter = RecentPostsPresenter(activity, model)
        model.presenter = presenter
        return presenter
    }

}