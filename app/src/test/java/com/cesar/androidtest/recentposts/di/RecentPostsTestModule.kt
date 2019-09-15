package com.cesar.androidtest.recentposts.di

import com.cesar.androidtest.recentposts.presentation.RecentPostsActivity
import com.cesar.androidtest.recentposts.RecentPostsContract
import com.cesar.androidtest.recentposts.model.RecentPostsRepository
import com.cesar.androidtest.recentposts.model.RecentPostsRepositoryImpl
import com.cesar.androidtest.recentposts.model.RecentPostsService
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import retrofit2.Retrofit

@Module
class RecentPostsTestModule(val activity: RecentPostsActivity) {

    @RecentPostsScope
    @Provides
    fun provideRecentPostsService(retrofit: Retrofit): RecentPostsService {
        return retrofit.create(RecentPostsService::class.java)
    }

    @RecentPostsScope
    @Provides
    fun provideRecentPostsRepository(): RecentPostsRepository {
        return Mockito.mock(RecentPostsRepositoryImpl::class.java)
    }

    @RecentPostsScope
    @Provides
    fun providePicasso(): Picasso {
        return Mockito.mock(Picasso::class.java)
    }

    @RecentPostsScope
    @Provides
    fun provideRecentPostsPresenter(): RecentPostsContract.Presenter {
        return Mockito.mock(RecentPostsContract.Presenter::class.java)
    }

}