package com.cesar.androidtest.recentposts.di

import com.cesar.androidtest.recentposts.RecentPostsActivity
import com.cesar.androidtest.recentposts.RecentPostsContract
import com.cesar.androidtest.recentposts.model.RecentPostsApi
import com.cesar.androidtest.recentposts.model.RecentPostsApiImpl
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
    fun recentPostsService(retrofit: Retrofit): RecentPostsService {
        return retrofit.create(RecentPostsService::class.java)
    }

    @RecentPostsScope
    @Provides
    fun recentPostsApi(service: RecentPostsService): RecentPostsApi {
        return Mockito.mock(RecentPostsApiImpl::class.java)
    }

    @RecentPostsScope
    @Provides
    fun picasso(): Picasso {
        return Mockito.mock(Picasso::class.java)
    }

    @RecentPostsScope
    @Provides
    fun recentPostsPresenter(api: RecentPostsApi): RecentPostsContract.Presenter {
        return Mockito.mock(RecentPostsContract.Presenter::class.java)
    }

}