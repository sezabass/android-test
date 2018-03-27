package com.cesar.androidtest.recentposts

import android.os.Bundle
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

class RecentPostsActivity : AppCompatActivity(), RecentPostsContract.View {

    @Inject
    lateinit var recentPostsPresenter: RecentPostsContract.Presenter

    private lateinit var adapter: RecyclerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var postsList: ArrayList<RecentPostModel> = ArrayList()
    lateinit var picasso: Picasso

    fun initializeDependencies() {

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

        recentPostsPresenter.onLoad()
    }

    private fun initializeRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        picasso = Picasso.Builder(this).build()

        // Set RecyclerView adapter
        adapter = RecyclerAdapter(postsList, picasso)
        recyclerView.adapter = adapter
    }

    override fun onListLoadingComplete(postsListResult: List<RecentPostModel>) {
        runOnUiThread {
            postsList.clear()
            postsList.addAll(postsListResult)
        }
        adapter.notifyDataSetChanged()
    }
}

