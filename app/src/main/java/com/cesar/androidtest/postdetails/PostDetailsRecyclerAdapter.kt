package com.cesar.androidtest.postdetails

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.cesar.androidtest.R
import com.cesar.androidtest.inflate
import com.cesar.androidtest.postdetails.model.Comment
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class PostDetailsRecyclerAdapter(private val context: Context,
                                 private val posts: List<Comment>) :
        RecyclerView.Adapter<PostDetailsRecyclerAdapter.PostHolder>() {

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val itemPost = posts[position]
        holder.bindPost(itemPost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row)
        return PostHolder(context, inflatedView)
    }

    class PostHolder(private var context: Context, v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        private var post: Comment? = null

        fun bindPost(post: Comment) {
            this.post = post
            view.itemBody.text = post.data?.body
            view.itemAuthor.text = post.data?.author
        }
    }
}
