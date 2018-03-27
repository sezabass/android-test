package com.cesar.androidtest.recentposts.di

import com.cesar.androidtest.recentposts.RecentPostsActivity
import com.cesar.androidtest.recentposts.RecentPostsModel
import com.cesar.androidtest.recentposts.RecentPostsPresenter
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
    fun recentPostsPresenter(service: RecentPostsService): RecentPostsPresenter {
        val model = RecentPostsModel(service)
        val presenter = RecentPostsPresenter(activity, model)
        model.presenter = presenter
        return presenter
    }

}