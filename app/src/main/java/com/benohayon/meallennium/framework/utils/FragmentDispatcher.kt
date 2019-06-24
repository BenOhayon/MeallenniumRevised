package com.benohayon.meallennium.framework.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.benohayon.meallennium.R

object FragmentDispatcher {

    fun replaceFragmentToActivity(activity: FragmentActivity, fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
                .replace(R.id.homeFragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
    }

}