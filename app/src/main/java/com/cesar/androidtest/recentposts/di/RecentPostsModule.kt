package com.cesar.androidtest.recentposts.di

import com.cesar.androidtest.recentposts.presentation.RecentPostsActivity
import com.cesar.androidtest.recentposts.RecentPostsContract
import com.cesar.androidtest.recentposts.domain.RecentPostsInteractor
import com.cesar.androidtest.recentposts.presentation.RecentPostsPresenter
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
    fun provideRecentPostsService(retrofit: Retrofit): RecentPostsService =
        retrofit.create(RecentPostsService::class.java)

    @RecentPostsScope
    @Provides
    fun provideRecentPostsApi(service: RecentPostsService): RecentPostsRepository =
        RecentPostsRepositoryImpl(service)

    @RecentPostsScope
    @Provides
    fun providePicasso() : Picasso = Picasso.Builder(activity).build()

    @RecentPostsScope
    @Provides
    fun provideRecentPostsPresenter(repository: RecentPostsRepository): RecentPostsContract.Presenter {
        val interactor = RecentPostsInteractor(repository)
        val presenter = RecentPostsPresenter(activity, interactor)
        interactor.presenter = presenter
        return presenter
    }

}