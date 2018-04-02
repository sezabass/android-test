package com.cesar.androidtest.postdetails

import com.cesar.androidtest.recentposts.model.RecentPostsApi


class PostDetailsModel(val api: RecentPostsApi) : PostDetailsContract.Model {

    var presenter: PostDetailsContract.Presenter? = null

}