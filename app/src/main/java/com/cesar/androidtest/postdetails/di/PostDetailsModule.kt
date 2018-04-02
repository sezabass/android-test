package com.cesar.androidtest.postdetails.di

import com.cesar.androidtest.postdetails.PostDetailsActivity
import com.cesar.androidtest.postdetails.PostDetailsContract
import com.cesar.androidtest.postdetails.PostDetailsModel
import com.cesar.androidtest.postdetails.PostDetailsPresenter
import com.cesar.androidtest.postdetails.model.PostDetailsApi
import com.cesar.androidtest.postdetails.model.PostDetailsApiImpl
import com.cesar.androidtest.postdetails.model.PostDetailsService
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PostDetailsModule(val activity: PostDetailsActivity){

    @PostDetailsScope
    @Provides
    fun picasso() : Picasso {
        return Picasso.Builder(activity).build()
    }

    @PostDetailsScope
    @Provides
    fun postDetailsService(retrofit: Retrofit): PostDetailsService {
        return retrofit.create(PostDetailsService::class.java)
    }

    @PostDetailsScope
    @Provides
    fun postDetailsApi(service: PostDetailsService): PostDetailsApi {
        return PostDetailsApiImpl(service)
    }

    @PostDetailsScope
    @Provides
    fun postDetailsPresenter(api: PostDetailsApi): PostDetailsContract.Presenter {
        val model = PostDetailsModel(api)
        val presenter = PostDetailsPresenter(activity, model)
        model.presenter = presenter
        return presenter
    }

}