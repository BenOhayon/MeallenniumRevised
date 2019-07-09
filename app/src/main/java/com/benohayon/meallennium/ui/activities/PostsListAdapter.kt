package com.benohayon.meallennium.ui.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.benohayon.meallennium.R
import com.benohayon.meallennium.framework.models.Post
import com.benohayon.meallennium.ui.custom_views.stylable.StylableTextView

class PostsListAdapter(val context: Context, private val list: List<Post>) : RecyclerView.Adapter<PostsListAdapter.PostsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsListViewHolder {
        return PostsListViewHolder(LayoutInflater.from(context).inflate(R.layout.posts_list_item, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PostsListViewHolder, position: Int) {
        val postForPosition = list[position]
        holder.postAuthorName.text = postForPosition.author
        holder.postSummery.text = postForPosition.summery
    }

    class PostsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val postAuthorName: StylableTextView = view.findViewById(R.id.postListItemAuthorName)
        val postSummery: StylableTextView = view.findViewById(R.id.postListItemPostContent)
        

    }
}