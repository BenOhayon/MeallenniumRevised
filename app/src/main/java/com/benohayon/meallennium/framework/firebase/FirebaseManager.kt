package com.benohayon.meallennium.framework.firebase

import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth

object FirebaseManager {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun createUserWithEmailAndPassword(email: String, password: String, onSuccess: () -> Unit, onFail: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) onSuccess()
                    else onFail(it.exception.toString())
                }
    }

    fun signInWithEmailAndPassword(email: String, password: String, onSuccess: () -> Unit, onFail: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) onSuccess()
                    else onFail(it.exception.toString())
                }
    }

}