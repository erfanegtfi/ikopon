package com.ikopon.ikopon.presentation.base.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ikopon.ikopon.presentation.base.adapter.ListItem

val BASE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListItem>() {

    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.listID == newItem.listID
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }
}