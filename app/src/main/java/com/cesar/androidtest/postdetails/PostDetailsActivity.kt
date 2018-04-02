package com.cesar.androidtest.postdetails

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.cesar.androidtest.R
import com.cesar.androidtest.httpclient.retrofit.di.DaggerNetworkComponent
import com.cesar.androidtest.postdetails.di.DaggerPostDetailsComponent
import com.cesar.androidtest.postdetails.di.PostDetailsModule
import com.cesar.androidtest.postdetails.model.Comment
import com.cesar.androidtest.recentposts.RecentPostsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_post_details.*
import javax.inject.Inject

open class PostDetailsActivity : AppCompatActivity(), PostDetailsContract.View {

    @Inject
    lateinit var picasso: Picasso
    @Inject
    lateinit var presenter: PostDetailsContract.Presenter

    private lateinit var postId: String
    private lateinit var postTitle: String
    private var postImageUrl: String? = null

    private lateinit var adapter: PostDetailsRecyclerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var commentsList: ArrayList<Comment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        val intent: Intent = intent
        postId = intent.extras.getString(RecentPostsActivity.KEY_POST_ID)
        postTitle = intent.extras.getString(RecentPostsActivity.KEY_POST_TITLE)

        if (intent.hasExtra(RecentPostsActivity.KEY_POST_IMAGE)) {
            postImageUrl = intent.extras.getString(RecentPostsActivity.KEY_POST_IMAGE)
        }

        initializeDependencies()

        initializeViews()

        initializeRecyclerView()
    }

    open fun initializeDependencies() {
        DaggerPostDetailsComponent.builder()
                .postDetailsModule(PostDetailsModule(this))
                .networkComponent(DaggerNetworkComponent.create())
                .build()
                .inject(this)
    }

    private fun initializeViews() {
        postDetailsTitle.text = postTitle
        if (postImageUrl != null) {
            picasso.load(postImageUrl).into(postDetailsImage)
        } else {
            postDetailsImage.visibility = View.GONE
        }
    }

    private fun initializeRecyclerView() {
        Log.v(TAG, "initializeRecyclerView")
        linearLayoutManager = LinearLayoutManager(this)
        postDetailsCommentList.layoutManager = linearLayoutManager

        adapter = PostDetailsRecyclerAdapter(this, commentsList, picasso)
        postDetailsCommentList.adapter = adapter
    }

    companion object {
        const val TAG = "PostDetailsActivity"
    }

}
