package com.cesar.androidtest.recentposts.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

interface RecentPostsRepository {
    fun list(lastViewed: String?, callback: ResultListener?)

    interface ResultListener {
        fun onResponseSuccessful(response: List<RecentPost>)
        fun onResponseNotSuccessful()
        fun onFailure(errorMessage: String)
    }
}

class RecentPostsRepositoryImpl @Inject constructor(
        private val service: RecentPostsService) : RecentPostsRepository {

    override fun list(lastViewed: String?, callback: RecentPostsRepository.ResultListener?) {

        val listCall: Call<RecentPost>? = service.list(
                limit = pageSize,
                count = pageSize,
                after = lastViewed
        )

        listCall?.enqueue(object : Callback<RecentPost> {
            override fun onResponse(call: Call<RecentPost>?, response: Response<RecentPost>?) {
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

            override fun onFailure(call: Call<RecentPost>?, t: Throwable?) {
                callback?.onFailure(t?.message.toString())
            }
        })
    }

    companion object {
        const val TAG = "API-RecentPosts"
        const val pageSize = 10
    }

}