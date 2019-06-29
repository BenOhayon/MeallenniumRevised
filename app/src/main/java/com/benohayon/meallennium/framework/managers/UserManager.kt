package com.benohayon.meallennium.framework.managers

import android.content.Context
import com.benohayon.meallennium.framework.utils.MeallenniumSharedPreferences

object UserManager {

    const val FIRST_NAME_KEY = "firstName"
    const val LAST_NAME_KEY = "lastName"
    const val EMAIL_KEY = "email"
    const val LOGIN_METHOD_KEY = "loginMethod"

    fun storeFirstName(context: Context, firstName: String?) {
        MeallenniumSharedPreferences(context)
                .storeString(FIRST_NAME_KEY, firstName)
    }

    fun getFirstName(context: Context, defaultFirstName: String?): String? {
        return MeallenniumSharedPreferences(context)
                .getString(FIRST_NAME_KEY, defaultFirstName)
    }

    fun storeLastName(context: Context, lastName: String?) {
        MeallenniumSharedPreferences(context)
                .storeString(LAST_NAME_KEY, lastName)
    }

    fun getLastName(context: Context, defaultLastName: String?): String? {
        return MeallenniumSharedPreferences(context)
                .getString(LAST_NAME_KEY, defaultLastName)
    }

    fun storeEmail(context: Context, email: String?) {
        MeallenniumSharedPreferences(context)
                .storeString(EMAIL_KEY, email)
    }

    fun getEmail(context: Context, defaultEmail: String?): String? {
        return MeallenniumSharedPreferences(context)
                .getString(EMAIL_KEY, defaultEmail)
    }

    fun storeLoginMethod(context: Context, loginMethod: FirebaseManager.LoginMethod?) {
        MeallenniumSharedPreferences(context)
                .storeLoginMethod(LOGIN_METHOD_KEY, loginMethod)
    }

    fun getLoginMethod(context: Context): FirebaseManager.LoginMethod? {
        return MeallenniumSharedPreferences(context)
                .getLoginMethod(LOGIN_METHOD_KEY)
    }

    fun clearValues(context: Context) {
        MeallenniumSharedPreferences(context)
                .clearValues()
    }

}