package com.ikopon.ikopon.presentation.common.dataBindingDelegate

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ikopon.ikopon.presentation.common.viewBindingDelegate.DialogFragmentViewBindingDelegate
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class BottomSheetFragmentDataBinding<out T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : ReadOnlyProperty<BottomSheetDialogFragment, T>, LifecycleObserver {

    private var binding: T? = null
    private var thisRef: BottomSheetDialogFragment? = null


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        this.thisRef?.viewLifecycleOwner?.lifecycle?.removeObserver(this)

        // Fragment.viewLifecycleOwner call LifecycleObserver.onDestroy() before Fragment.onDestroyView().
        // That's why we need to postpone reset of the viewBinding
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post {
            Log.v("onDestroyonDestroyyy", "onDestroyonDestroy")
            binding = null
        }
    }


    override operator fun getValue(thisRef: BottomSheetDialogFragment, property: KProperty<*>): T {
        this.thisRef = thisRef
        thisRef.viewLifecycleOwner.lifecycle.addObserver(this)

        return binding ?: createBinding(thisRef).also { binding = it }
    }

    private fun createBinding(fragment: BottomSheetDialogFragment): T {
        return DataBindingUtil.inflate(fragment.layoutInflater, layoutRes, null, false)
    }
}

fun <T : ViewBinding> BottomSheetDialogFragment.viewBinding(viewBindingFactory: (View) -> T) =
    DialogFragmentViewBindingDelegate(this, viewBindingFactory)

//override val binding: DialogCreatePollBinding by DialogFragmentDataBinding(R.layout.dialog_create_poll)