package com.cesar.androidtest.recentposts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cesar.androidtest.R
import com.cesar.androidtest.httpclient.retrofit.di.DaggerNetworkComponent
import com.cesar.androidtest.recentposts.di.DaggerRecentPostsComponent
import com.cesar.androidtest.recentposts.di.RecentPostsModule
import javax.inject.Inject

class RecentPostsActivity : AppCompatActivity(), RecentPostsContract.View {

    @Inject
    lateinit var recentPostsPresenter: RecentPostsPresenter

    fun initializeDependencies() {

        DaggerRecentPostsComponent.builder()
                .recentPostsModule(RecentPostsModule(this))
                .networkComponent(DaggerNetworkComponent.create())
                .build()
                .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeDependencies()

        recentPostsPresenter.onLoad()

        // TODO
        //val recyclerView = posts_list_recyclerview
        //recyclerView.adapter = PostsListAdapter(recentPosts(), this)
    }

}
