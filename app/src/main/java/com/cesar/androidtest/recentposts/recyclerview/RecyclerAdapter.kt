package com.cesar.androidtest.recentposts.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.cesar.androidtest.R
import com.cesar.androidtest.recentposts.RecentPostsActivity
import com.cesar.androidtest.recentposts.model.RecentPostModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class RecyclerAdapter(private val context: Context,
                      private val posts: List<RecentPostModel>,
                      private val picasso: Picasso) :
        RecyclerView.Adapter<RecyclerAdapter.PostHolder>() {

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: RecyclerAdapter.PostHolder, position: Int) {
        val itemPost = posts[position]
        holder.bindPost(itemPost, picasso)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.PostHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row)
        return PostHolder(context, inflatedView)
    }

    class PostHolder(private var context: Context, v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var post: RecentPostModel? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            (this.context as RecentPostsActivity).onPostsListItemClicked(v, post)
        }

        fun bindPost(post: RecentPostModel, picasso: Picasso) {

            this.post = post
            view.itemTitle.text = post.data?.title
            view.itemAuthor.text = post.data?.author

            val imageUrl = post.imageUrl()
            if (imageUrl != null) {
                picasso.load(imageUrl).into(view.itemImage)
            } else {
                // hide image view
                val imageParams: LinearLayout.LayoutParams =
                        view.itemImage.layoutParams as LinearLayout.LayoutParams
                imageParams.weight = 0f
                view.itemImage.layoutParams = imageParams

                // stretch text
                val descriptionParams: LinearLayout.LayoutParams =
                        view.itemDescriptionHolder.layoutParams as LinearLayout.LayoutParams
                descriptionParams.weight = 3f
                view.itemDescriptionHolder.layoutParams = descriptionParams

            }
        }
    }
}
