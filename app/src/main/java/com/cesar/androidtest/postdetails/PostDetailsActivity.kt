package com.cesar.androidtest.postdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.cesar.androidtest.R
import com.cesar.androidtest.httpclient.retrofit.di.DaggerNetworkComponent
import com.cesar.androidtest.postdetails.di.DaggerPostDetailsComponent
import com.cesar.androidtest.postdetails.di.PostDetailsModule
import com.cesar.androidtest.postdetails.model.Comment
import com.cesar.androidtest.recentposts.presentation.RecentPostsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_post_details.*
import javax.inject.Inject

open class PostDetailsActivity : AppCompatActivity(), PostDetailsContract.View {

    @Inject
    lateinit var picasso: Picasso
    @Inject
    lateinit var presenter: PostDetailsContract.Presenter

    private var postId: String? = null
    private var postTitle: String? = null
    private var postImageUrl: String? = null
    private var postUrl: String? = null

    private lateinit var adapter: PostDetailsRecyclerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var commentsList: ArrayList<Comment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        val intent: Intent = intent
        postId = intent.extras!!.getString(RecentPostsActivity.KEY_POST_ID)
        postTitle = intent.extras!!.getString(RecentPostsActivity.KEY_POST_TITLE)

        if (intent.hasExtra(RecentPostsActivity.KEY_POST_IMAGE)) {
            postImageUrl = intent.extras!!.getString(RecentPostsActivity.KEY_POST_IMAGE)
        }

        if (intent.hasExtra(RecentPostsActivity.KEY_POST_URL)) {
            postUrl = intent.extras!!.getString(RecentPostsActivity.KEY_POST_URL)
        }

        initializeDependencies()
        initializeViews()
        initializeRecyclerView()

        presenter.onLoad(postId!!)
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

        if (postUrl != null) {
            buttonOpenOnBrowser.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(this, Uri.parse(postUrl))
            }

        } else {
            buttonOpenOnBrowser.visibility = View.GONE
        }
    }

    private fun initializeRecyclerView() {
        Log.v(TAG, "initializeRecyclerView")
        linearLayoutManager = LinearLayoutManager(this)
        postDetailsCommentList.layoutManager = linearLayoutManager

        adapter = PostDetailsRecyclerAdapter(this, commentsList)
        postDetailsCommentList.adapter = adapter
    }

    override fun onLoadCommentsSuccess(response: List<Comment>) {
        runOnUiThread {
            commentsList.clear()
            commentsList.addAll(response)
            adapter.notifyDataSetChanged()
        }
    }
    override fun onLoadCommentsFailure() {
        // TODO
    }

    companion object {
        const val TAG = "PostDetailsActivity"
    }

}
