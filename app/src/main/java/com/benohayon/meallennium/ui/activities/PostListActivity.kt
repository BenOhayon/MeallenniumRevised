package com.benohayon.meallennium.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import com.benohayon.meallennium.R
import com.benohayon.meallennium.framework.managers.UserManager
import com.benohayon.meallennium.ui.activities.abs.BaseActivity


class PostListActivity : BaseActivity() {

    private val TAG = "PostListActivity"

    override val layoutResource: Int
        get() = R.layout.activity_post_list

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val firstName = UserManager.getFirstName(this, "default")
        val lastName = UserManager.getLastName(this, "default")
        val email = UserManager.getEmail(this, "default")

        Log.d(TAG, "onCreate: first name = $firstName, last name = $lastName, email = $email")

        progressBar = findViewById(R.id.postListScreenProgressBar)
    }
}
