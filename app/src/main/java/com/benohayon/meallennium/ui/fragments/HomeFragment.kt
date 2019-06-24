package com.benohayon.meallennium.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.benohayon.meallennium.R
import com.benohayon.meallennium.framework.utils.FragmentDispatcher
import com.benohayon.meallennium.ui.custom_views.DontHaveAccountFrame

class HomeFragment : Fragment() {

    private var dontHaveAccountFrame: DontHaveAccountFrame? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        dontHaveAccountFrame = view.findViewById(R.id.homeScreenDontHaveAccountFrame)
        dontHaveAccountFrame?.setOnClickListener {
            FragmentDispatcher.replaceFragmentToActivity(activity!!, SignUpFragment())
        }
        return view
    }
}
