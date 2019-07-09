package com.benohayon.meallennium.ui.activities

import android.os.Bundle
import com.benohayon.meallennium.R
import com.benohayon.meallennium.framework.utils.FragmentDispatcher
import com.benohayon.meallennium.ui.activities.abs.BaseActivity
import com.benohayon.meallennium.ui.fragments.HomeFragment

class HomeActivity : BaseActivity() {

    override val layoutResource: Int
        get() = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FragmentDispatcher.moveToFragment(this, HomeFragment(), R.id.homeFragmentContainer)
    }
}
