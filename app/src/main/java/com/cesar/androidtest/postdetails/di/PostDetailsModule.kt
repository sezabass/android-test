package com.cesar.androidtest.postdetails.di

import com.cesar.androidtest.postdetails.PostDetailsActivity
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
class PostDetailsModule(val activity: PostDetailsActivity){

    @PostDetailsScope
    @Provides
    fun picasso() : Picasso {
        return Picasso.Builder(activity).build()
    }

}