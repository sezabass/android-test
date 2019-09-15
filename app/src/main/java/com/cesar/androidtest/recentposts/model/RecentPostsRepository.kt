package com.cesar.androidtest.recentposts.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface RecentPostsRepository {
    fun list(lastViewed: String?, callback: ResultListener?)

    interface ResultListener {
        fun onResponseSuccessful(response: List<RecentPostModel>)
        fun onResponseNotSuccessful()
        fun onFailure(errorMessage: String)
    }
}

class RecentPostsRepositoryImpl(val service: RecentPostsService) : RecentPostsRepository {

    override fun list(lastViewed: String?, callback: RecentPostsRepository.ResultListener?) {

        val listCall: Call<RecentPostModel>? = service.list(
                limit = pageSize,
                count = pageSize,
                after = lastViewed
        )

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

    companion object {
        const val TAG = "API-RecentPosts"
        const val pageSize = 10
    }

}