package com.benohayon.meallennium.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.benohayon.meallennium.R
import com.benohayon.meallennium.framework.managers.FirebaseManager
import com.benohayon.meallennium.ui.activities.abs.BaseActivity

class SplashActivity : BaseActivity() {

    override val layoutResource: Int
        get() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkLoginStatus()
    }

    private fun openHomeScreen() {
        Handler().postDelayed({
            val toSignInActivity = Intent(this, HomeActivity::class.java)
            startActivity(toSignInActivity)
            finish()
        }, 3000)
    }

    private fun checkLoginStatus() {
        FirebaseManager.checkLoginStatus(this, userLoggedInCallback = {
            startActivity(Intent(this, PostListActivity::class.java))
        }, userLoggedOutCallback = {
            openHomeScreen()
        })
    }

}
