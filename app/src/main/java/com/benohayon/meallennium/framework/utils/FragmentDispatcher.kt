package com.benohayon.meallennium.framework.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

object FragmentDispatcher {

    fun moveToFragment(activity: FragmentActivity, fragment: Fragment, containerRes: Int) {
        activity.supportFragmentManager.beginTransaction()
                .replace(containerRes, fragment)
                .addToBackStack(null)
                .commit()
    }

    fun popFragmentFromBackStack(activity: FragmentActivity) {
        activity.supportFragmentManager.popBackStack()
    }

}