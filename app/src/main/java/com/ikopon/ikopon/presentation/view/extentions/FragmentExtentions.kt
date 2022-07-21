package com.ikopon.ikopon.presentation.view.extentions

import androidx.fragment.app.Fragment
import com.ikopon.ikopon.core.utils.GeneralError


fun Fragment.onMessageToast(message: String?) {
    requireContext().onMessageToast(message)
}

fun Fragment.onMessageToast(message: Int?) {
    requireContext().onMessageToast(message)
}

fun Fragment.onMessageToast(error: GeneralError?) {
    requireContext().onMessageToast(error)
}

fun Fragment.onMessageSnackbar(error: GeneralError?) {
    requireContext().onMessageSnackbar(error)
}

fun Fragment.onMessageSnackbar(message: String?) {
    requireContext().onMessageSnackbar(message)
}

fun Fragment.onErrorDialog(message: String?) {
    requireContext().onErrorDialog(message)
}

fun Fragment.onSuccessDialog(message: String?) {
    requireContext().onSuccessDialog(message)
}