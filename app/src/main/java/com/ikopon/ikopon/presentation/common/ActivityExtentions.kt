package com.ikopon.ikopon.presentation.common

import android.content.Context
import android.content.res.Resources
import com.ikopon.ikopon.data.utils.GeneralError
import com.ikopon.ikopon.presentation.base.BaseViewActions

inline fun <T1 : Any, T2 : Any, R : Any> safeNullCheck(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()



fun Context.onMessageToast(message: String?) {
    if (message != null)
        BaseViewActions.onMessageToast(context = this, message)
}

fun Context.onMessageToast(message: Int?) {
    if (message != null)
        onMessageToast(getString(message))
}

fun Context.onMessageToast(error: GeneralError?) {
    if (error != null)
        BaseViewActions.onMessageToast(context = this, error)
}

fun Context.onMessageSnackbar(error: GeneralError?) {
    if (error != null)
        BaseViewActions.onMessageSnackbar(context = this, error)
}

fun Context.onMessageSnackbar(message: String?) {
    if (message != null)
        BaseViewActions.onMessageSnackbar(context = this, message)
}

fun Context.onErrorDialog(message: String?) {
    if (message != null)
        BaseViewActions.onErrorDialog(context = this, message)
}

fun Context.onSuccessDialog(message: String?) {
    if (message != null)
        BaseViewActions.onSuccessDialog(context = this, message)
}