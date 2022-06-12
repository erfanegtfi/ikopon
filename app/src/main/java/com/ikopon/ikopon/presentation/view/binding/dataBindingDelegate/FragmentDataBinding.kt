package com.ikopon.ikopon.presentation.view.binding.dataBindingDelegate

import androidx.lifecycle.Lifecycle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class FragmentDataBinding<out T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : ReadOnlyProperty<Fragment, T>, LifecycleObserver {

    private var binding: T? = null
    private var thisRef: Fragment? = null


    override operator fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        this.thisRef = thisRef

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
            val lifecycle = thisRef.viewLifecycleOwner.lifecycle
            if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
                throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
            }

            createBinding(thisRef).also { binding = it }
        }

    }

    private fun createBinding(fragment: Fragment): T {
        return DataBindingUtil.inflate(fragment.layoutInflater, layoutRes, null, false)
    }
}
