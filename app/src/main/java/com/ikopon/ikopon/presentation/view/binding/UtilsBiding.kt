package com.ikopon.ikopon.presentation.common.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ikopon.ikopon.presentation.view.loading.LoadState
import com.ikopon.ikopon.presentation.view.loading.LoadingProgressView

@BindingAdapter("image_url")
fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(this.context)
        .load(imageUrl)
        .centerCrop()
//        .placeholder(R.drawable.error_center_x)
        .into(this);
}

@BindingAdapter("list_load_state")
fun LoadingProgressView.loadState(loadState: LoadState?) {

    when (loadState) {
        is LoadState.Initial -> {
            init()
        }
        is LoadState.Loading -> {
            loading()
        }
        is LoadState.Loaded -> {
            loaded()
        }
        is LoadState.Error -> {
            setErrorText(loadState.message)
            this.error()
        }
    }
}