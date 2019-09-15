package com.cesar.androidtest.recentposts.presentation.recyclerview

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.cesar.androidtest.R
import com.cesar.androidtest.inflate
import com.cesar.androidtest.recentposts.presentation.RecentPostsActivity
import com.cesar.androidtest.recentposts.model.RecentPostModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class RecyclerAdapter(private val context: Context,
                      private val posts: List<RecentPostModel>,
                      private val picasso: Picasso) :
        RecyclerView.Adapter<RecyclerAdapter.PostHolder>() {

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val itemPost = posts[position]
        holder.bindPost(itemPost, picasso)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row)

        val imageParams = inflatedView.itemImage.layoutParams
                as LinearLayout.LayoutParams
        val descriptionParams = inflatedView.itemDescriptionHolder.layoutParams
                as LinearLayout.LayoutParams

        return PostHolder(context, inflatedView, imageParams, descriptionParams)
    }

    class PostHolder(private var context: Context,
                     v: View,
                     private val imageParams: LinearLayout.LayoutParams,
                     private val descriptionParams: LinearLayout.LayoutParams) :
            RecyclerView.ViewHolder(v), View.OnClickListener {

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
            view.itemBody.text = post.data?.title
            view.itemAuthor.text = post.data?.author

            val imageUrl = post.imageUrl()
            if (imageUrl != null) {
                adaptStyleWithImage()
                picasso.load(imageUrl).into(view.itemImage)
            } else {
                adaptStyleWithoutImage()
            }
        }

        private fun adaptStyleWithImage() {
            // reset image view
            imageParams.weight = IMAGE_SHOWN_IMAGE_WEIGHT
            view.itemImage.layoutParams = imageParams
            // reset text
            descriptionParams.weight = IMAGE_SHOWN_TEXT_WEIGHT
            view.itemDescriptionHolder.layoutParams = descriptionParams
        }

        private fun adaptStyleWithoutImage() {
            // hide image view
            imageParams.weight = IMAGE_HIDDEN_IMAGE_WEIGHT
            view.itemImage.layoutParams = imageParams

            // stretch text
            descriptionParams.weight = IMAGE_HIDDEN_TEXT_WEIGHT
            view.itemDescriptionHolder.layoutParams = descriptionParams
        }

        companion object {
            private const val IMAGE_SHOWN_IMAGE_WEIGHT = 1f
            private const val IMAGE_SHOWN_TEXT_WEIGHT = 2f
            private const val IMAGE_HIDDEN_IMAGE_WEIGHT = 0f
            private const val IMAGE_HIDDEN_TEXT_WEIGHT = 3f
        }
    }
}
