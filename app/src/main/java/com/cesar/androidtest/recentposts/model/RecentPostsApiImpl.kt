package com.cesar.androidtest.recentposts.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecentPostsApiImpl(val service: RecentPostsService) : RecentPostsApi {

    companion object {
        val TAG = "API-RecentPosts"
    }

    override fun list(callback: RecentPostsApi.ResultListener?) {

        val listCall: Call<RecentPostModel>? = service.list()

        listCall?.enqueue(object : Callback<RecentPostModel> {
            override fun onResponse(call: Call<RecentPostModel>?, response: Response<RecentPostModel>?) {
                if (response!!.isSuccessful) {
                    response.body()?.data?.children?.let {
                        callback?.onResponseSuccessful(it.toMutableList())
                        return
                    }
                    callback?.onResponseNotSuccessful()
                } else {
                    callback?.onResponseNotSuccessful()
                }
            }

            override fun onFailure(call: Call<RecentPostModel>?, t: Throwable?) {
                callback?.onFailure(t?.message.toString())
            }
        })
    }

}