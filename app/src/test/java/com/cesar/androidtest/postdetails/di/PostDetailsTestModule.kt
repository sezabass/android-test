package com.cesar.androidtest.postdetails.di

import com.cesar.androidtest.postdetails.PostDetailsActivity
import com.cesar.androidtest.postdetails.PostDetailsContract
import com.cesar.androidtest.postdetails.model.PostDetailsApi
import com.cesar.androidtest.postdetails.model.PostDetailsApiImpl
import com.cesar.androidtest.postdetails.model.PostDetailsService
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import retrofit2.Retrofit

@Module
class PostDetailsTestModule(val activity: PostDetailsActivity) {

    @PostDetailsScope
    @Provides
    fun postDetailsService(retrofit: Retrofit): PostDetailsService {
        return retrofit.create(PostDetailsService::class.java)
    }

    @PostDetailsScope
    @Provides
    fun postDetailsApi(service: PostDetailsService): PostDetailsApi {
        return Mockito.mock(PostDetailsApiImpl::class.java)
    }

    @PostDetailsScope
    @Provides
    fun picasso(): Picasso {
        return Mockito.mock(Picasso::class.java)
    }

    @PostDetailsScope
    @Provides
    fun postDetailsPresenter(api: PostDetailsApi): PostDetailsContract.Presenter {
        return Mockito.mock(PostDetailsContract.Presenter::class.java)
    }

}