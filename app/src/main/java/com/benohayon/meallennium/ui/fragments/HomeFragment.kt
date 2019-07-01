package com.benohayon.meallennium.ui.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.benohayon.meallennium.R
import com.benohayon.meallennium.framework.managers.FirebaseManager
import com.benohayon.meallennium.framework.managers.UserManager
import com.benohayon.meallennium.framework.utils.AlertPrompter
import com.benohayon.meallennium.framework.utils.FragmentDispatcher
import com.benohayon.meallennium.ui.activities.PostListActivity
import com.benohayon.meallennium.ui.custom_views.DontHaveAccountFrame
import com.benohayon.meallennium.ui.custom_views.FacebookSignInButton
import com.benohayon.meallennium.ui.custom_views.GoogleSignInButton
import com.benohayon.meallennium.ui.custom_views.stylable.StylableTextView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider


class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"
    private val GOOGLE_SIGN_IN_REQUEST_CODE = 4567

    private lateinit var dontHaveAccountFrame: DontHaveAccountFrame
    private lateinit var signInWithEmailButton: StylableTextView
    private lateinit var facebookSignInButton: FacebookSignInButton
    private lateinit var googleSignInButton: GoogleSignInButton
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        progressBar = view.findViewById(R.id.homeScreenProgressBar)
        dontHaveAccountFrame = view.findViewById(R.id.homeScreenDontHaveAccountFrame)
        dontHaveAccountFrame.setOnClickListener {
            FragmentDispatcher.moveToFragment(activity!!, SignUpFragment())
        }

        facebookSignInButton = view.findViewById(R.id.homeScreenSignInWithFacebookButton)
        facebookSignInButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            FirebaseManager.connectWithFacebook(this, onSuccess = {
                progressBar.visibility = View.INVISIBLE
                UserManager.storeLoginMethod(activity!!, FirebaseManager.LoginMethod.Facebook)
                val toPostListActivity = Intent(activity!!, PostListActivity::class.java)
                startActivity(toPostListActivity)
                activity!!.finish()
            }, onFail = {
                progressBar.visibility = View.INVISIBLE
                AlertPrompter.showInfoDialog(activity!!, getString(R.string.alert_user_authentication_failed_title), it)
            }, onCancel = {
                progressBar.visibility = View.INVISIBLE
                AlertPrompter.showInfoDialog(activity!!, getString(R.string.alert_user_authentication_canceled_title), getString(R.string.alert_user_authentication_canceled_message))
            })
        }

        googleSignInButton = view.findViewById(R.id.homeScreenSignInWithGoogleButton)
        googleSignInButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build()
            val googleSignInClient = GoogleSignIn.getClient(activity!!, gso)
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE)
        }

        signInWithEmailButton = view.findViewById(R.id.homeScreenSignInWithEmailButton)
        signInWithEmailButton.setOnClickListener {
            FragmentDispatcher.moveToFragment(activity!!, SignInFragment())
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)

                // move to main activity
                val credential = GoogleAuthProvider.getCredential(account?.id, null)
                FirebaseManager.auth.signInWithCredential(credential)
                        .addOnCompleteListener(activity!!) {
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                progressBar.visibility = View.INVISIBLE
                                UserManager.storeLoginMethod(activity!!, FirebaseManager.LoginMethod.Google)
                                UserManager.storeFirstName(activity!!, account?.displayName)
                                UserManager.storeEmail(activity!!, account?.email)
                                startActivity(Intent(activity!!, PostListActivity::class.java))
                                activity!!.finish()
                            } else {
                                // If sign in fails, display a message to the user.
                                progressBar.visibility = View.INVISIBLE
                                AlertPrompter.showInfoDialog(activity!!, getString(R.string.alert_user_authentication_failed_title), task.exception?.message!!)
                            }
                        }
            } catch (e: ApiException) {
                progressBar.visibility = View.INVISIBLE
                AlertPrompter.showInfoDialog(activity!!, getString(R.string.alert_user_authentication_failed_title), e.message!!)
            }
        } else
            FirebaseManager.setCallbackManagerOnActivityResult(requestCode, resultCode, data)

        super.onActivityResult(requestCode, resultCode, data)
    }
}
