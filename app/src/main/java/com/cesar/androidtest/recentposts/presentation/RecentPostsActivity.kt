package com.cesar.androidtest.recentposts.presentation

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.View
import com.cesar.androidtest.R
import com.cesar.androidtest.httpclient.retrofit.di.DaggerNetworkComponent
import com.cesar.androidtest.postdetails.PostDetailsActivity
import com.cesar.androidtest.recentposts.RecentPostsContract
import com.cesar.androidtest.recentposts.di.DaggerRecentPostsComponent
import com.cesar.androidtest.recentposts.di.RecentPostsModule
import com.cesar.androidtest.recentposts.model.RecentPost
import com.cesar.androidtest.recentposts.presentation.recyclerview.EndlessRecyclerViewScrollListener
import com.cesar.androidtest.recentposts.presentation.recyclerview.RecyclerAdapter
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
    private var postsList: ArrayList<RecentPost> = ArrayList()
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
                loadNextDataFromApi()
            }
        }
        recyclerView.addOnScrollListener(scrollListener)
    }

    fun loadNextDataFromApi() {
        var lastName: String? = null
        if (!postsList.isEmpty()) {
            lastName = postsList.last().data?.name
        }
        presenter.requestMoreItems(lastName)
    }

    override fun onRefresh() {
        presenter.onSwipeToRefresh()
    }

    override fun hideLoading() {
        recentPostsSwipeRefreshLayout.isRefreshing = false
    }

    override fun onListLoadingComplete(postsListResult: List<RecentPost>) {
        runOnUiThread {
            postsList.clear()
            postsList.addAll(postsListResult)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onListAddingComplete(response: List<RecentPost>) {
        runOnUiThread {
            postsList.addAll(response)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onPostsListItemClicked(listItem: View, post: RecentPost?) {
        presenter.onPostsListItemClicked(post)
    }

    override fun showPostDetails(post: RecentPost?) {
        val postId = post?.data?.id
        val postTitle = post?.data?.title
        val postImage = post?.imageUrl()
        val postUrl = post?.data?.url
        Log.v(TAG, "Post details on $postId requested!")

        val postDetailsIntent = Intent(this, PostDetailsActivity::class.java)
        postDetailsIntent.putExtra(KEY_POST_ID, postId)
        postDetailsIntent.putExtra(KEY_POST_TITLE, postTitle)
        if (postImage != null) {
            postDetailsIntent.putExtra(KEY_POST_IMAGE, postImage)
        }
        if (postUrl != null) {
            postDetailsIntent.putExtra(KEY_POST_URL, postUrl)
        }
        this.startActivity(postDetailsIntent)
    }

    override fun onRequestListResponseNotSuccessful() {
        val message = applicationContext.getString(
                R.string.message_error_list_request_not_successful)
        createSnackBar(message)
    }

    override fun onRequestListFailure() {
        val message = applicationContext.getString(
                R.string.message_error_list_request_failure)
        createSnackBar(message)
    }

    private fun createSnackBar(message: String) {
        val snackbar: Snackbar = Snackbar.make(recentPostsLayout, message,
                Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(R.string.retry) { loadNextDataFromApi() }
        snackbar.show()
    }

    companion object {
        const val TAG = "RecentPostsActivity"
        const val KEY_POST_ID = "POST_ID"
        const val KEY_POST_TITLE= "POST_TITLE"
        const val KEY_POST_IMAGE = "POST_IMAGE"
        const val KEY_POST_URL = "POST_URL"
    }
}

