package com.benohayon.meallennium.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import com.benohayon.meallennium.R
import com.benohayon.meallennium.framework.utils.AlertPrompter
import com.benohayon.meallennium.ui.custom_views.stylable.StylableTextView
import kotlinx.android.synthetic.*

class SignUpFragment : Fragment() {

    private var emailEt: EditText? = null
    private var passwordEt: EditText? = null
    private var confirmPasswordEt: EditText? = null
    private var backButton: StylableTextView? = null
    private var signUpButton: StylableTextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        backButton = view.findViewById(R.id.signUpScreenBackButton)
        backButton?.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        signUpButton = view.findViewById(R.id.signUpScreenSignUpButton)
        signUpButton?.setOnClickListener {
            if (fieldsValidated()) {

            }
        }

        return view
    }

    private fun fieldsValidated() : Boolean {
        var flag = true
        val invalidatedBackground = resources.getDrawable(R.drawable.invalidated_field_background, null)

        if (emailEt?.text?.length == 0) {
            flag = false
            emailEt?.background = invalidatedBackground
        }

        if (passwordEt?.text?.length == 0) {
            flag = false
            passwordEt?.background = invalidatedBackground
        }

        if (confirmPasswordEt?.text?.length == 0) {
            flag = false
            confirmPasswordEt?.background = invalidatedBackground
        }

        if (passwordEt?.text?.toString() != confirmPasswordEt?.text?.toString()) {
            flag = false
            AlertPrompter.showInfoDialog(activity!!, "Validation Error", "Your password was not confirmed properly")
        }

        return flag
    }
}
