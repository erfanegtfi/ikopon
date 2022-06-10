package com.ikopon.ikopon.presentation.common.viewBindingDelegate

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 *
 * How to use:
 * - Call it inside your fragment
 * private val binding: FragmentSampleBinding by viewBinding()
 *
 * using reflection
 */

//class FragmentViewBindingDelegate<T : ViewBinding>(
//    bindingClass: Class<T>,
//    private val fragment: Fragment
//) : ReadOnlyProperty<Fragment, T> {
//
//    private var binding: T? = null
//    private val bindMethod = bindingClass.getMethod("bind", View::class.java)
//
//    init {
//        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
//            val viewLifecycleOwnerLiveDataObserver =
//                Observer<LifecycleOwner?> {
//                    val viewLifecycleOwner = it ?: return@Observer
//
//                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
//                        override fun onDestroy(owner: LifecycleOwner) {
//                            binding = null
//                        }
//                    })
//                }
//
//            override fun onCreate(owner: LifecycleOwner) {
//                fragment.viewLifecycleOwnerLiveData.observeForever(viewLifecycleOwnerLiveDataObserver)
//            }
//
//            override fun onDestroy(owner: LifecycleOwner) {
//                fragment.viewLifecycleOwnerLiveData.removeObserver(viewLifecycleOwnerLiveDataObserver)
//            }
//        })
//    }
//
//    @Suppress("UNCHECKED_CAST")
//    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
//        binding?.let { return it }
//
//
//        val lifecycle = fragment.viewLifecycleOwner.lifecycle
//        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
//            error("Cannot access view bindings. View lifecycle is ${lifecycle.currentState}!")
//        }
//

//        val invoke = bindMethod.invoke(null, thisRef.requireView()) as T
//
//        return invoke.also { this.binding = it }
//    }
//}

//class FragmentViewBindingDelegate2<T : ViewBinding>(
//    bindingClass: Class<T>,
//    private val fragment: Fragment
//) : ReadOnlyProperty<Fragment, T> {
//
//    private var binding: T? = null
//    private val bindMethod = bindingClass.getMethod("bind", View::class.java)
//
//    @Suppress("UNCHECKED_CAST")
//    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
//        binding?.let { return it }
//
//        /**
//         * Adding observer to the fragment lifecycle
//         */
//        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
//            override fun onCreate(owner: LifecycleOwner) {
//                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
//                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
//                        override fun onDestroy(owner: LifecycleOwner) {
//                            /**
//                             * Clear the binding when Fragment lifecycle called the onDestroy
//                             */
//                            binding = null
//                        }
//                    })
//                }
//            }
//        })
//
//        val lifecycle = fragment.viewLifecycleOwner.lifecycle
//        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
//            error("Cannot access view bindings. View lifecycle is ${lifecycle.currentState}!")
//        }
//
//
//        val invoke = bindMethod.invoke(null, thisRef.requireView()) as T
//
//        return invoke.also { this.binding = it }
//    }
//}

//inline fun <reified T : ViewBinding> Fragment.viewBinding() = FragmentViewBindingDelegate(T::class.java, this)

//https://zhuinden.medium.com/simple-one-liner-viewbinding-in-fragments-and-activities-with-kotlin-961430c6c07c

/**
 *
 * How to use:
 * - Call it inside your fragment
 * private val binding: FragmentSampleBinding by viewBinding()
 * FragmentSampleBinding::bind
 */

//class FragmentViewBindingDelegate2<T : ViewBinding>(
//    val fragment: Fragment,
//    val viewBindingFactory: (View) -> T
//) : ReadOnlyProperty<Fragment, T> {
//    private var binding: T? = null
//
//    init {
//        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
//            val viewLifecycleOwnerLiveDataObserver =
//                Observer<LifecycleOwner?> {
//                    val viewLifecycleOwner = it ?: return@Observer
//
//                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
//                        override fun onDestroy(owner: LifecycleOwner) {
//                            binding = null
//                        }
//                    })
//                }
//
//            override fun onCreate(owner: LifecycleOwner) {
//                fragment.viewLifecycleOwnerLiveData.observeForever(viewLifecycleOwnerLiveDataObserver)
//            }
//
//            override fun onDestroy(owner: LifecycleOwner) {
//                fragment.viewLifecycleOwnerLiveData.removeObserver(viewLifecycleOwnerLiveDataObserver)
//            }
//        })
//    }
//
//    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
//        val binding = binding
////        Log.v("getValuegetValue", "${thisRef.id} ${fragment.id}")
//
//        val lifecycle = fragment.viewLifecycleOwner.lifecycle
//        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
//            throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
//        }
//
//        return  binding ?: viewBindingFactory(thisRef.requireView()).also { this.binding = it }
//    }
//}

class FragmentViewBindingDelegate<T : ViewBinding>(
    val fragment: Fragment,
    val viewBindingFactory: (View) -> T
) : ReadOnlyProperty<Fragment, T>, LifecycleObserver {
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


    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val binding = binding
//        Log.v("getValuegetValue", "${thisRef.id} ${fragment.id}")
        fragment.viewLifecycleOwner.lifecycle.addObserver(this)

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
        }

        return binding ?: viewBindingFactory(thisRef.requireView()).also { this.binding = it }
    }
}

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

//private val binding by viewBinding(MainActivityBinding::inflate)

