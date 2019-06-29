package com.benohayon.meallennium.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.benohayon.meallennium.R
import com.benohayon.meallennium.framework.managers.FirebaseManager
import com.benohayon.meallennium.framework.managers.UserManager
import com.benohayon.meallennium.ui.activities.abs.BaseActivity
import com.benohayon.meallennium.ui.custom_views.stylable.StylableTextView


class PostListActivity : BaseActivity() {

    private val TAG = "PostListActivity"

    override val layoutResource: Int
        get() = R.layout.activity_post_list

    private var progressBar: ProgressBar? = null
    private var signOutButton: StylableTextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val firstName = UserManager.getFirstName(this, "default")
        val lastName = UserManager.getLastName(this, "default")
        val email = UserManager.getEmail(this, "default")

        Log.d(TAG, "onCreate: first name = $firstName, last name = $lastName, email = $email")

        progressBar = findViewById(R.id.postListScreenProgressBar)
        signOutButton = findViewById(R.id.postListScreenSignOutButton)

        signOutButton?.setOnClickListener {
            progressBar?.visibility = View.VISIBLE
            FirebaseManager.signOut(this, onComplete = {
                progressBar?.visibility = View.INVISIBLE
                val backToHomeActivity = Intent(this, HomeActivity::class.java)
                startActivity(backToHomeActivity)
                finish()
            })
        }
    }
}
