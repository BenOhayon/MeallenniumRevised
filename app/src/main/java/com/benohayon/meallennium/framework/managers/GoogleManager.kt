package com.benohayon.meallennium.framework.managers

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions




object GoogleManager {

    val googleSignInOptions = GoogleSignInOptions.Builder()
            .requestEmail()
            .build()

    fun signOut() {

    }

    fun checkLoginStatus(context: Context, userLoggedInCallback: () -> Unit, userLoggedOutCallback: () -> Unit) {
        val account = GoogleSignIn.getLastSignedInAccount(context)
        if (account != null) { // user logged in
            UserManager.storeFirstName(context, account.displayName)
            UserManager.storeEmail(context, account.email)
            userLoggedInCallback()
        } else
            userLoggedOutCallback()
    }

}