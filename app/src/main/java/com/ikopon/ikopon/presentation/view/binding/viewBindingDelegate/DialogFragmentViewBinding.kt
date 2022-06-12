package com.ikopon.ikopon.presentation.view.binding.viewBindingDelegate

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
 * private val binding by viewBinding(MainActivityBinding::binding)
 *
 */

class DialogFragmentViewBindingDelegate<T : ViewBinding>(
    val fragment: DialogFragment,
    val viewBindingFactory: (View) -> T
) : ReadOnlyProperty<DialogFragment, T> , LifecycleObserver {
    private var binding: T? = null



    override fun getValue(thisRef: DialogFragment, property: KProperty<*>): T {
        thisRef.lifecycle.addObserver(object : DefaultLifecycleObserver {
            val viewLifecycleOwnerLiveDataObserver =
                Observer<LifecycleOwner?> {
                    val viewLifecycleOwner = it ?: return@Observer

                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            binding = null
                        }
                    })
                }

            override fun onCreate(owner: LifecycleOwner) {
                thisRef.viewLifecycleOwnerLiveData.observeForever(viewLifecycleOwnerLiveDataObserver)
            }

            override fun onDestroy(owner: LifecycleOwner) {
                thisRef.viewLifecycleOwnerLiveData.removeObserver(viewLifecycleOwnerLiveDataObserver)
            }
        })


        return binding ?: run {
            val lifecycle = fragment.viewLifecycleOwner.lifecycle
            if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
                throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
            }

            viewBindingFactory(thisRef.requireView()).also {
                this.binding = it
            }
        }

    }
}

fun <T : ViewBinding> DialogFragment.viewBinding(viewBindingFactory: (View) -> T) =
    DialogFragmentViewBindingDelegate(this, viewBindingFactory)

//private val binding by viewBinding(MainActivityBinding::binding)