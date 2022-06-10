package com.ikopon.ikopon.presentation.common.dataBindingDelegate

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class ActivityDataBinding<out T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : ReadOnlyProperty<AppCompatActivity, T>, LifecycleObserver {

    private var binding: T? = null
    private var thisRef: AppCompatActivity? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        this.thisRef?.lifecycle?.removeObserver(this)
        binding = null
    }

    override operator fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        thisRef.lifecycle.addObserver(this)
        return binding ?: createBinding(thisRef).also { binding = it }
    }

    private fun createBinding(activity: Activity): T {
        return DataBindingUtil.setContentView(activity, layoutRes)
    }
}