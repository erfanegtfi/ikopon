package com.ikopon.ikopon.presentation.view.binding.dataBindingDelegate

import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ikopon.ikopon.presentation.view.binding.viewBindingDelegate.DialogFragmentViewBindingDelegate
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class BottomSheetFragmentDataBinding<out T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : ReadOnlyProperty<BottomSheetDialogFragment, T>, LifecycleObserver {

    private var binding: T? = null
    private var thisRef: BottomSheetDialogFragment? = null


    override operator fun getValue(thisRef: BottomSheetDialogFragment, property: KProperty<*>): T {
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

    private fun createBinding(fragment: BottomSheetDialogFragment): T {
        return DataBindingUtil.inflate(fragment.layoutInflater, layoutRes, null, false)
    }
}

fun <T : ViewBinding> BottomSheetDialogFragment.viewBinding(viewBindingFactory: (View) -> T) =
    DialogFragmentViewBindingDelegate(this, viewBindingFactory)

//override val binding: DialogCreatePollBinding by DialogFragmentDataBinding(R.layout.dialog_create_poll)