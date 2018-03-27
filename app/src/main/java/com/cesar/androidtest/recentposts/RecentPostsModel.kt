package com.cesar.androidtest.recentposts

import android.util.Log
import com.cesar.androidtest.recentposts.model.RecentPostModel
import com.cesar.androidtest.recentposts.model.RecentPostsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Inject


class RecentPostsModel (val service: RecentPostsService
) : RecentPostsContract.Model {

    var presenter: RecentPostsContract.Presenter? = null

    override fun requestList() {

        val call: Call<RecentPostModel>? = service.list()

        call?.enqueue(object : Callback<RecentPostModel> {
            override fun onResponse(call: Call<RecentPostModel>?, response: Response<RecentPostModel>?) {
                response?.body()?.data?.children?.let {
                    Log.v("Retrofit", it[0].data?.title)
                    //val recentPosts: RecentPostModel = it
                }
            }

            override fun onFailure(call: Call<RecentPostModel>?, t: Throwable?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.d("Retrofit", "TODO!")
            }
        })

        presenter?.onRequestListSuccess()
    }

}