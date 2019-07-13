package com.benohayon.meallennium.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.benohayon.meallennium.R
import com.benohayon.meallennium.framework.managers.FirebaseManager
import com.benohayon.meallennium.framework.managers.UserManager
import com.benohayon.meallennium.framework.utils.AlertPrompter
import com.benohayon.meallennium.framework.utils.FragmentDispatcher
import com.benohayon.meallennium.ui.activities.abs.BaseActivity
import com.benohayon.meallennium.ui.custom_views.MeallenniumTopActionBar
import com.benohayon.meallennium.ui.custom_views.stylable.StylableTextView
import com.benohayon.meallennium.ui.fragments.MyPostsFragment
import com.benohayon.meallennium.ui.fragments.PostListFragment
import com.benohayon.meallennium.ui.fragments.SettingsFragment
import com.google.android.material.navigation.NavigationView

class PostListActivity : BaseActivity() {

    private val TAG = "PostListActivity"

    override val layoutResource: Int
        get() = R.layout.activity_post_list

    private lateinit var meallenniumTopActionBar: MeallenniumTopActionBar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = UserManager.getName(this, "default")
        val email = UserManager.getEmail(this, "default")

        Log.d(TAG, "onCreate: first name = $name, email = $email")

        initTopBar()
        initUI()

        FragmentDispatcher.moveToFragment(this, PostListFragment(), R.id.postListActivityContainer)
        navigationView.setCheckedItem(R.id.navigationDrawerMenuHomeOption)
    }

    private fun initUI() {
        progressBar = findViewById(R.id.postListActivityProgressBar)
        initNavigationDrawer()
    }

    private fun initNavigationDrawer() {
        drawerLayout = findViewById(R.id.postListActivityDrawerLayout)
        navigationView = findViewById(R.id.postListActivityNavigationView)
        val userEmailTextView: StylableTextView = navigationView.getHeaderView(0).findViewById(R.id.navigationDrawerHeadlineUserEmail)
        val userNameTextView: StylableTextView = navigationView.getHeaderView(0).findViewById(R.id.navigationDrawerHeadlineUserName)
        userEmailTextView.text = UserManager.getEmail(this)
        userNameTextView.text = UserManager.getName(this)

        navigationView.setNavigationItemSelectedListener { item: MenuItem ->
            drawerLayout.closeDrawer(GravityCompat.START)

            when (item.itemId) {
                R.id.navigationDrawerMenuHomeOption ->
                    FragmentDispatcher.moveToFragment(this, PostListFragment(), R.id.postListActivityContainer)

                R.id.navigationDrawerMenuMyPostsOption ->
                    FragmentDispatcher.moveToFragment(this, MyPostsFragment(), R.id.postListActivityContainer)

                R.id.navigationDrawerMenuSettingsOption ->
                    FragmentDispatcher.moveToFragment(this, SettingsFragment(), R.id.postListActivityContainer)

                R.id.navigationDrawerMenuSignOutOption -> requestSignOut()

                R.id.navigationDrawerMenuAboutOption -> {
                    startActivity(Intent(this, AboutActivity::class.java))
                }
            }

            true
        }
    }

    private fun requestSignOut() {
        AlertPrompter.showConfirmationDialog(this, getString(R.string.alert_sign_out_title), getString(R.string.alert_sign_out_message),
                onYesClick = { dialog, which ->
                    progressBar.visibility = View.VISIBLE
                    FirebaseManager.signOut(this) {
                        progressBar.visibility = View.INVISIBLE
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    }
                })
    }

    private fun initTopBar() {
        meallenniumTopActionBar = findViewById(R.id.postListActivityTopBar)
        meallenniumTopActionBar.setLeftButtonResource(R.mipmap.navigation_drawer_icon)
        meallenniumTopActionBar.setRightButtonResource(R.mipmap.search_icon)
        meallenniumTopActionBar.centerText = getString(R.string.post_list_screen_top_action_bar_center_text)
        meallenniumTopActionBar.setLeftButtonOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        meallenniumTopActionBar.setRightButtonOnClickListener {
            // Opening the search post search screen
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            requestSignOut()
    }
}
