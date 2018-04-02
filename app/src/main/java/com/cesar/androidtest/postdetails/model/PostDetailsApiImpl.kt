package com.cesar.androidtest.postdetails.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostDetailsApiImpl(val service: PostDetailsService) : PostDetailsApi {

    override fun list(id: String, callback: PostDetailsApi.ResultListener?) {

        val listCall: Call<CommentModel>? = service.commentsList(id)

        listCall?.enqueue(object : Callback<CommentModel> {
            override fun onResponse(call: Call<CommentModel>?, response: Response<CommentModel>?) {
                if (response!!.isSuccessful) {
                    response.body()?.comments?.get(1)?.data?.children?.let {
                        callback?.onResponseSuccessful(it.toMutableList())
                        return
                    }
                    callback?.onResponseNotSuccessful()
                } else {
                    callback?.onResponseNotSuccessful()
                }
            }

            override fun onFailure(call: Call<CommentModel>?, t: Throwable?) {
                callback?.onFailure(t?.message.toString())
            }
        })
    }

    companion object {
        const val TAG = "API-PostDetails"
    }

}