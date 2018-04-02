package com.cesar.androidtest.postdetails

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.cesar.androidtest.R
import com.cesar.androidtest.inflate
import com.cesar.androidtest.postdetails.model.Comment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class PostDetailsRecyclerAdapter(private val context: Context,
                                 private val posts: List<Comment>,
                                 private val picasso: Picasso) :
        RecyclerView.Adapter<PostDetailsRecyclerAdapter.PostHolder>() {

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostDetailsRecyclerAdapter.PostHolder, position: Int) {
        val itemPost = posts[position]
        holder.bindPost(itemPost, picasso)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostDetailsRecyclerAdapter.PostHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row)
        return PostHolder(context, inflatedView)
    }

    class PostHolder(private var context: Context, v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        private var post: Comment? = null

        fun bindPost(post: Comment, picasso: Picasso) {
            this.post = post
            view.itemBody.text = post.data?.body
            view.itemAuthor.text = post.data?.author
        }
    }
}
