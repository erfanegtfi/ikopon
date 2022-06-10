package com.ikopon.ikopon.presentation.common.viewBindingDelegate

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by Yoga C. Pranata on 5/6/21.
 * Android Engineer
 *
 * How to use:
 * - Call it inside your DialogFragment
 * private val binding: DialogSampleBinding by viewBinding()
 *
 * using reflection
 */
//inline fun <reified T : ViewBinding> DialogFragment.viewBinding() = DialogFragmentViewBindingDelegate(T::class.java)
//
//class DialogFragmentViewBindingDelegate<T : ViewBinding>(private val bindingClass: Class<T>) :
//    ReadOnlyProperty<DialogFragment, T> {
//    /**
//     * initiate variable for binding view
//     */
//    private var binding: T? = null
//
//    @Suppress("UNCHECKED_CAST")
//    override fun getValue(thisRef: DialogFragment, property: KProperty<*>): T {
//        binding?.let { return it }
//
//        val inflateMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java)
//        val invokeLayout = inflateMethod.invoke(null, LayoutInflater.from(thisRef.context)) as T
//
//        return invokeLayout.also { this.binding = it }
//    }
//}

/**
 * Created by Yoga C. Pranata on 5/6/21.
 * Android Engineer
 *
 * How to use:
 * - Call it inside your DialogFragment
 * private val binding by viewBinding(MainActivityBinding::binding)
 *
 */

class DialogFragmentViewBindingDelegate<T : ViewBinding>(
    val fragment: DialogFragment,
    val viewBindingFactory: (View) -> T
) : ReadOnlyProperty<DialogFragment, T> , LifecycleObserver {
    private var binding: T? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        this.fragment.viewLifecycleOwner.lifecycle.removeObserver(this)

        // Fragment.viewLifecycleOwner call LifecycleObserver.onDestroy() before Fragment.onDestroyView().
        // That's why we need to postpone reset of the viewBinding
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post {
            Log.v("onDestroyonDestroyyy", "onDestroyonDestroy ${fragment.id}")
            binding = null
        }
    }


    override fun getValue(thisRef: DialogFragment, property: KProperty<*>): T {
        val binding = binding
//        Log.v("getValuegetValue", "${thisRef.id} ${fragment.id}")
        fragment.viewLifecycleOwner.lifecycle.addObserver(this)


//        val lifecycle = fragment.viewLifecycleOwner.lifecycle
//        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
//            throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
//        }

        return binding ?: viewBindingFactory(thisRef.requireView()).also { this.binding = it }
    }
}

fun <T : ViewBinding> DialogFragment.viewBinding(viewBindingFactory: (View) -> T) =
    DialogFragmentViewBindingDelegate(this, viewBindingFactory)

//private val binding by viewBinding(MainActivityBinding::binding)