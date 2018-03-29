package com.cesar.androidtest.recentposts.di

import com.cesar.androidtest.recentposts.RecentPostsActivity
import com.cesar.androidtest.recentposts.RecentPostsContract
import com.cesar.androidtest.recentposts.RecentPostsModel
import com.cesar.androidtest.recentposts.RecentPostsPresenter
import com.cesar.androidtest.recentposts.model.RecentPostsApi
import com.cesar.androidtest.recentposts.model.RecentPostsApiImpl
import com.cesar.androidtest.recentposts.model.RecentPostsService
import com.squareup.picasso.Picasso
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
    fun recentPostsApi(service: RecentPostsService): RecentPostsApi {
        return RecentPostsApiImpl(service)
    }

    @RecentPostsScope
    @Provides
    fun picasso() : Picasso {
        return Picasso.Builder(activity).build()
    }

    @RecentPostsScope
    @Provides
    fun recentPostsPresenter(api: RecentPostsApi): RecentPostsContract.Presenter {
        val model = RecentPostsModel(api)
        val presenter = RecentPostsPresenter(activity, model)
        model.presenter = presenter
        return presenter
    }

}