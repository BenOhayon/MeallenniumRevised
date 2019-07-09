package com.benohayon.meallennium.framework.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.benohayon.meallennium.R

object AlertPrompter {

    fun showInfoDialog(context: Context, title: String, message: String) {
        val alert = AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(context.resources.getString(R.string.alert_ok_button_text), null)
                .create()

        alert.show()
    }

    fun showConfirmationDialog(context: Context, title: String, message: String, onYesClick: (dialog: DialogInterface, which: Int) -> Unit, onNoClick: (dialog: DialogInterface, which: Int) -> Unit) {
        val alert = AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(context.resources.getString(R.string.alert_yes_button_text), onYesClick)
                .setNegativeButton(context.resources.getString(R.string.alert_no_button_text), onNoClick)
                .create()

        alert.show()

    }

    fun showConfirmationDialog(context: Context, title: String, message: String, onYesClick: (dialog: DialogInterface, which: Int) -> Unit) {
        showConfirmationDialog(context, title, message, onYesClick, {_, _ ->  })
    }

}