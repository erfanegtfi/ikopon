package com.ikopon.ikopon.presentation.view.binding.viewBindingDelegate

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 *
 * How to use:
 * - Call it inside your activity
 * private val binding: HomepageBinding by viewBinding(HomepageBinding::inflate)
 *
 */
inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        val invokeLayout =  bindingInflater.invoke(layoutInflater)
//        setContentView(invokeLayout.root)
        invokeLayout
    }