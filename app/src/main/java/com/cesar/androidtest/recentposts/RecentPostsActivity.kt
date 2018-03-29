package com.cesar.androidtest.recentposts

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.cesar.androidtest.R
import com.cesar.androidtest.httpclient.retrofit.di.DaggerNetworkComponent
import com.cesar.androidtest.recentposts.di.DaggerRecentPostsComponent
import com.cesar.androidtest.recentposts.di.RecentPostsModule
import com.cesar.androidtest.recentposts.model.RecentPostModel
import com.cesar.androidtest.recentposts.recyclerview.RecyclerAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_recentposts.*
import javax.inject.Inject

class RecentPostsActivity : AppCompatActivity(), RecentPostsContract.View,
        SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var recentPostsPresenter: RecentPostsContract.Presenter
    @Inject
    lateinit var picasso: Picasso

    private lateinit var adapter: RecyclerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var postsList: ArrayList<RecentPostModel> = ArrayList()

    private fun initializeDependencies() {

        DaggerRecentPostsComponent.builder()
                .recentPostsModule(RecentPostsModule(this))
                .networkComponent(DaggerNetworkComponent.create())
                .build()
                .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recentposts)

        initializeDependencies()

        initializeRecyclerView()

        recentPostsSwipeRefreshLayout.setOnRefreshListener(this)

        recentPostsPresenter.onLoad()
    }

    private fun initializeRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        // Set RecyclerView adapter
        adapter = RecyclerAdapter(postsList, picasso)
        recyclerView.adapter = adapter
    }

    override fun onRefresh() {
        recentPostsPresenter.onSwipeToRefresh()
    }

    override fun hideLoading() {
        recentPostsSwipeRefreshLayout.isRefreshing = false
    }

    override fun onListLoadingComplete(postsListResult: List<RecentPostModel>) {
        runOnUiThread {
            postsList.clear()
            postsList.addAll(postsListResult)
        }
        adapter.notifyDataSetChanged()
    }
}

