package com.benohayon.meallennium.framework.managers

import android.content.Context
import android.os.Bundle
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import com.facebook.login.LoginManager

object FacebookManager {

    fun checkLoginStatus(context: Context, userLoggedInCallback: () -> Unit, userLoggedOutCallback: () -> Unit) {
        val currentAccessToken: AccessToken? = AccessToken.getCurrentAccessToken()

        if (currentAccessToken != null) {
            loadFacebookUserDetails(context, currentAccessToken, userLoggedInCallback)
        } else {
            userLoggedOutCallback()
        }
    }

    fun signOutFromFacebook(onComplete: () -> Unit) {
        if (AccessToken.getCurrentAccessToken() == null) {
            return  // already logged out
        }

        GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, GraphRequest.Callback {
            LoginManager.getInstance().logOut()
            onComplete()
        }).executeAsync()
    }

    private fun loadFacebookUserDetails(context: Context, accessToken: AccessToken?, userLoggedInCallback: () -> Unit) {
        val loadUserDetailsRequest: GraphRequest = GraphRequest.newMeRequest(accessToken) { jsonObject, response ->
            // save the user account details
            UserManager.storeFirstName(context, jsonObject.getString("first_name"))
            UserManager.storeLastName(context, jsonObject.getString("last_name"))
            UserManager.storeEmail(context, jsonObject.getString("email"))
            userLoggedInCallback()
        }

        val parameters = Bundle()
        parameters.putString("fields", "first_name,last_name,email")
        loadUserDetailsRequest.parameters = parameters
        loadUserDetailsRequest.executeAsync()
    }

}