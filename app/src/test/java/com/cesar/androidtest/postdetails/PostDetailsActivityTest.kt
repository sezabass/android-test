package com.cesar.androidtest.postdetails

import android.content.Intent
import com.cesar.androidtest.postdetails.di.PostDetailsTestActivityMock
import com.cesar.androidtest.recentposts.presentation.RecentPostsActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment


@RunWith(RobolectricTestRunner::class)
class PostDetailsActivityTest {
    private lateinit var activity: PostDetailsActivity

    @Before
    fun setUp() {
        val intent = Intent(RuntimeEnvironment.application, PostDetailsTestActivityMock::class.java)
        intent.putExtra(RecentPostsActivity.KEY_POST_ID, "samplePostId")
        intent.putExtra(RecentPostsActivity.KEY_POST_TITLE, "samplePostTitle")

        activity = Robolectric.buildActivity(PostDetailsTestActivityMock::class.java, intent).create().get()
    }

    @Test
    fun whenLoadActivityThenRequestCommentsList() {
        Mockito.verify(activity.presenter).onLoad(anyString())
    }

}