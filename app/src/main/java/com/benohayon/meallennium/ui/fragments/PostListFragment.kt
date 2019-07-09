package com.benohayon.meallennium.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.benohayon.meallennium.R
import com.benohayon.meallennium.framework.models.Post
import com.benohayon.meallennium.ui.activities.PostsListAdapter

class PostListFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var postsRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_post_list, container, false)

        progressBar = view.findViewById(R.id.postListFragmentProgressBar)
        postsRecyclerView = view.findViewById(R.id.postListFragmentRecyclerView)

        val layoutManager = LinearLayoutManager(activity!!)
        postsRecyclerView.layoutManager = layoutManager
        val postsListAdapter = PostsListAdapter(activity!!, listOf(
                Post("This is summery for post 1", "This is content for post 1", "logo url for post 1", "Author for post 1"),
                Post("This is summery for post 2", "This is content for post 2", "logo url for post 2", "Author for post 2"),
                Post("This is summery for post 3", "This is content for post 3", "logo url for post 3", "Author for post 3"),
                Post("This is summery for post 4", "This is content for post 4", "logo url for post 4", "Author for post 4"),
                Post("This is summery for post 5", "This is content for post 5", "logo url for post 5", "Author for post 5"),
                Post("This is summery for post 6", "This is content for post 6", "logo url for post 6", "Author for post 6"),
                Post("This is summery for post 7", "This is content for post 7", "logo url for post 7", "Author for post 7")
        ))

        postsRecyclerView.adapter = postsListAdapter

        return view
    }
}
