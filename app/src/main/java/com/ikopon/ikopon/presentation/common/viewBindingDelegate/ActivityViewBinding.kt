package com.ikopon.ikopon.presentation.common.viewBindingDelegate

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 *
 * How to use:
 * - Call it inside your activity
 * private val binding: HomepageBinding by viewBinding()
 *
 * using reflection
 */
//inline fun <reified T : ViewBinding> Activity.viewBinding() = ActivityViewBindingDelegate(T::class.java)
//
//class ActivityViewBindingDelegate<T : ViewBinding>(private val bindingClass: Class<T>) : ReadOnlyProperty<Activity, T> {
//    private var binding: T? = null
//
//    @Suppress("UNCHECKED_CAST")
//    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
//        binding?.let { return it }
//
//        val inflateMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java)
//        val invokeLayout = inflateMethod.invoke(null, thisRef.layoutInflater) as T
//        thisRef.setContentView(invokeLayout.root)
//
//        return invokeLayout.also { this.binding = it }
//    }
//}

//https://zhuinden.medium.com/simple-one-liner-viewbinding-in-fragments-and-activities-with-kotlin-961430c6c07c
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