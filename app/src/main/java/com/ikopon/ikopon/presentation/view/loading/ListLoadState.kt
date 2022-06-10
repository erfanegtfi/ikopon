package com.ikopon.ikopon.presentation.view.loading;


sealed class LoadState {
    object Initial : LoadState()
    object Loading : LoadState()
    object Loaded : LoadState()
    data class Error(val message: String? = "") : LoadState()
}