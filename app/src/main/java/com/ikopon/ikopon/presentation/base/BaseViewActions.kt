package com.ikopon.ikopon.presentation.base;

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.ikopon.ikopon.core.utils.GeneralError

object BaseViewActions {


    fun onMessageToast(context: Context, error: GeneralError) {
        if (error.message != null)
            onMessageToast(context, error.message)
        else if (error.messageRes != null && error.messageRes != 0)
            onMessageToast(context, error.messageRes!!)
    }

    fun onMessageToast(context: Context, message: Int) {
        onMessageToast(context, context.getString(message))
    }

    fun onMessageSnackbar(context: Context, message: Int) {
        onMessageSnackbar(context, context.getString(message))
    }

    fun onMessageSnackbar(context: Context, error: GeneralError) {
        if (error.message != null)
            onMessageSnackbar(context, error.message)
        else if (error.messageRes != null && error.messageRes != 0)
            onMessageSnackbar(context, error.messageRes!!)
    }

    fun onMessageSnackbar(context: Context, message: String?) {
        if (message != null)
            Snackbar.make(View(context), message, Snackbar.LENGTH_LONG).show()
    }


    fun onMessageToast(context: Context, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }


    fun onErrorDialog(context: Context, message: String?) {
//        if (message != null) {
//            val mProgressDialog = MessageUtils.showErrorDialog(context, message)
//            mProgressDialog!!.show()
//        }
    }

    fun onSuccessDialog(context: Context, message: String?) {
//        if (message != null) {
//            val mProgressDialog = MessageUtils.showSuccessDialog(context, message)
//            mProgressDialog!!.show()
//        }
    }

}
