package com.cesar.androidtest.recentposts

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.cesar.androidtest.R
import com.cesar.androidtest.httpclient.retrofit.di.DaggerNetworkComponent
import com.cesar.androidtest.recentposts.di.DaggerRecentPostsComponent
import com.cesar.androidtest.recentposts.di.RecentPostsModule
import com.cesar.androidtest.recentposts.model.RecentPostModel
import com.cesar.androidtest.recentposts.recyclerview.EndlessRecyclerViewScrollListener
import com.cesar.androidtest.recentposts.recyclerview.RecyclerAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_recentposts.*
import javax.inject.Inject

open class RecentPostsActivity : AppCompatActivity(), RecentPostsContract.View,
        SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: RecentPostsContract.Presenter
    @Inject
    lateinit var picasso: Picasso

    private lateinit var adapter: RecyclerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var postsList: ArrayList<RecentPostModel> = ArrayList()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    open fun initializeDependencies() {
        DaggerRecentPostsComponent.builder()
                .recentPostsModule(RecentPostsModule(this))
                .networkComponent(DaggerNetworkComponent.create())
                .build()
                .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recentposts)

        this.initializeDependencies()

        initializeRecyclerView()

        recentPostsSwipeRefreshLayout.setOnRefreshListener(this)

        presenter.onLoad()
    }

    private fun initializeRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        adapter = RecyclerAdapter(this, postsList, picasso)
        recyclerView.adapter = adapter

        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                loadNextDataFromApi(page)
            }
        }
        recyclerView.addOnScrollListener(scrollListener)
    }

    fun loadNextDataFromApi(page: Int) {
        presenter.requestMoreItems()
        // TODO: Append the new data objects to the existing set of items inside the array of items
        // TODO: Notify the adapter of the new items made with `notifyItemRangeInserted()`
    }

    override fun onRefresh() {
        presenter.onSwipeToRefresh()
    }

    override fun hideLoading() {
        recentPostsSwipeRefreshLayout.isRefreshing = false
    }

    override fun onListLoadingComplete(postsListResult: List<RecentPostModel>) {
        runOnUiThread {
            postsList.clear()
            postsList.addAll(postsListResult)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onPostsListItemClicked(listItem: View) {
        presenter.onPostsListItemClicked()
    }
    override fun showPostDetails() {
        Log.v("RecentPostsActivity", "Post details requested!")
    }
}

