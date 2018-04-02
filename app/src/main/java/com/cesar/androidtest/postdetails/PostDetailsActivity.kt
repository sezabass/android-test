package com.cesar.androidtest.postdetails

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cesar.androidtest.R
import com.cesar.androidtest.recentposts.RecentPostsActivity
import kotlinx.android.synthetic.main.activity_post_details.*

class PostDetailsActivity : AppCompatActivity() {

    lateinit var postId: String
    lateinit var postTitle: String
    lateinit var postImageUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        val intent: Intent = intent
        postId = intent.extras.getString(RecentPostsActivity.KEY_POST_ID)
        postTitle = intent.extras.getString(RecentPostsActivity.KEY_POST_TITLE)
        postImageUrl = intent.extras.getString(RecentPostsActivity.KEY_POST_IMAGE)

        postDetailsTitle.text = postTitle
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        Log.v(TAG, "initializeRecyclerView")
    }

    companion object {
        const val TAG = "PostDetailsActivity"
    }

}
