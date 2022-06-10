package com.ikopon.ikopon.presentation.base;

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.ikopon.ikopon.model.base.ApiBaseResponse
import com.ikopon.ikopon.presentation.view.loading.LoadState
import kotlinx.coroutines.flow.MutableSharedFlow

open class BaseViewModel : ViewModel() {

    var loadingState: ObservableField<LoadState> = ObservableField(LoadState.Initial)
    val sharedApiCallEvents = MutableSharedFlow<ViewState<ApiBaseResponse>>()

    override fun onCleared() {
        Log.v("disposables", "disposables")
        super.onCleared()
    }
}
