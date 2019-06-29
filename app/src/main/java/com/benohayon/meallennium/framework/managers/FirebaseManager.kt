package com.benohayon.meallennium.framework.managers

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest


object FirebaseManager {

    enum class LoginMethod {
        EmailPassword, Facebook, Google
    }

    private val firebaseUser: FirebaseUser?
        get() = auth.currentUser

     val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val callbackManager: CallbackManager = CallbackManager.Factory.create()
    private val facebookLoginManager: LoginManager = LoginManager.getInstance()

    fun createUserWithEmailAndPassword(name: String, email: String, password: String, onSuccess: () -> Unit, onFail: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { createUserTask ->

                    if (createUserTask.isSuccessful) {
                        val profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName(name).build()

                        firebaseUser?.updateProfile(profileUpdates)
                                ?.addOnCompleteListener { updateUserTask ->
                                    if (updateUserTask.isComplete)
                                        onSuccess()
                                }
                    } else onFail(createUserTask.exception.toString())
                }
    }

    fun signInWithEmailAndPassword(email: String, password: String, onSuccess: () -> Unit, onFail: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) onSuccess()
                    else onFail(it.exception.toString())
                }
    }

    fun connectWithGoogle() {

    }

    fun connectWithFacebook(fragment: Fragment, onSuccess: () -> Unit, onFail: (String) -> Unit, onCancel: () -> Unit) {
        facebookLoginManager.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                onSuccess()
            }

            override fun onCancel() {
                onCancel()
            }

            override fun onError(error: FacebookException?) {
                onFail(error?.message!!)
            }
        })

        facebookLoginManager.logInWithReadPermissions(fragment, mutableListOf("email", "public_profile"))
    }

    fun setCallbackManagerOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun signOutFromFacebook(onComplete: () -> Unit) {
        FacebookManager.signOutFromFacebook(onComplete)
    }

    private fun signOutFromGoogle(activity: FragmentActivity, onComplete: () -> Unit) {
        auth.signOut()

        // Google sign out
        val mGoogleSignInClient = GoogleSignIn.getClient(activity, GoogleManager.googleSignInOptions)
        mGoogleSignInClient.signOut().addOnCompleteListener(activity) { onComplete() }
    }

    fun checkLoginStatus(context: Context, userLoggedInCallback: () -> Unit, userLoggedOutCallback: () -> Unit) {
        val loginMethod = UserManager.getLoginMethod(context)
        if (loginMethod != null) {
            when (loginMethod) {
                LoginMethod.EmailPassword -> {
                    if (firebaseUser != null) {
                        UserManager.storeFirstName(context, firebaseUser?.displayName)
                        UserManager.storeEmail(context, firebaseUser?.email)
                        userLoggedInCallback()
                    } else
                        userLoggedOutCallback()
                }

                LoginMethod.Facebook -> {
                    FacebookManager.checkLoginStatus(context, userLoggedInCallback, userLoggedOutCallback)
                }

                LoginMethod.Google -> {
                    GoogleManager.checkLoginStatus(context, userLoggedInCallback, userLoggedOutCallback)
                }
            }
        } else
            userLoggedOutCallback()
    }

    fun signOut(context: Context, onComplete: () -> Unit) {
        when (UserManager.getLoginMethod(context)) {
            FirebaseManager.LoginMethod.Facebook -> {
                auth.signOut()
                signOutFromFacebook {
                    UserManager.clearValues(context)
                    onComplete()
                }
            }

            FirebaseManager.LoginMethod.Google -> {
                signOutFromGoogle(context as FragmentActivity) {
                    UserManager.clearValues(context)
                    onComplete()
                }
            }

            FirebaseManager.LoginMethod.EmailPassword -> {
                auth.signOut()
                UserManager.clearValues(context)
                onComplete()
            }
        }
    }

}