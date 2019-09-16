package com.cesar.androidtest.recentposts.di

import com.cesar.androidtest.recentposts.RecentPostsContract
import com.cesar.androidtest.recentposts.domain.RecentPostsInteractor
import com.cesar.androidtest.recentposts.model.RecentPostsRepository
import com.cesar.androidtest.recentposts.model.RecentPostsRepositoryImpl
import com.cesar.androidtest.recentposts.model.RecentPostsService
import com.cesar.androidtest.recentposts.presentation.RecentPostsActivity
import com.cesar.androidtest.recentposts.presentation.RecentPostsPresenter
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class RecentPostsModule(val activity: RecentPostsActivity) {

    @RecentPostsScope
    @Provides
    fun providePicasso(): Picasso = Picasso.Builder(activity).build()

    @RecentPostsScope
    @Provides
    fun provideRecentPostsService(retrofit: Retrofit): RecentPostsService = retrofit.create()

    @RecentPostsScope
    @Provides
    fun provideRecentPostsRepository(repository: RecentPostsRepositoryImpl): RecentPostsRepository = repository

    @RecentPostsScope
    @Provides
    fun providesRecentPostsInteractor(interactor: RecentPostsInteractor): RecentPostsContract.Interactor = interactor

    @RecentPostsScope
    @Provides
    fun provideRecentPostsPresenter(interactor: RecentPostsInteractor): RecentPostsContract.Presenter {
        return RecentPostsPresenter(interactor).apply {
            view = activity
        }
    }

}