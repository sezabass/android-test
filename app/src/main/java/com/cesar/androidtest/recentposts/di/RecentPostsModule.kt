package com.cesar.androidtest.recentposts.di

import com.cesar.androidtest.recentposts.RecentPostsActivity
import com.cesar.androidtest.recentposts.RecentPostsContract
import com.cesar.androidtest.recentposts.RecentPostsInteractor
import com.cesar.androidtest.recentposts.RecentPostsPresenter
import com.cesar.androidtest.recentposts.model.RecentPostsRepository
import com.cesar.androidtest.recentposts.model.RecentPostsRepositoryImpl
import com.cesar.androidtest.recentposts.model.RecentPostsService
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RecentPostsModule(val activity: RecentPostsActivity){

    @RecentPostsScope
    @Provides
    fun recentPostsService(retrofit: Retrofit): RecentPostsService =
        retrofit.create(RecentPostsService::class.java)

    @RecentPostsScope
    @Provides
    fun recentPostsApi(service: RecentPostsService): RecentPostsRepository =
        RecentPostsRepositoryImpl(service)

    @RecentPostsScope
    @Provides
    fun picasso() : Picasso = Picasso.Builder(activity).build()

    @RecentPostsScope
    @Provides
    fun recentPostsPresenter(repository: RecentPostsRepository): RecentPostsContract.Presenter {
        val interactor = RecentPostsInteractor(repository)
        val presenter = RecentPostsPresenter(activity, interactor)
        interactor.presenter = presenter
        return presenter
    }

}