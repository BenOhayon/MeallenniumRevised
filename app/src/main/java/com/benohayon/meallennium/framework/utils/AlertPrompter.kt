package com.benohayon.meallennium.framework.utils

import android.app.AlertDialog
import android.content.Context

object AlertPrompter {

    fun showInfoDialog(context: Context, title: String, message: String) {
        val alert = AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .create()

        alert.show()
    }

}