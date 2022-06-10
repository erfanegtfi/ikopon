package com.ikopon.ikopon.presentation.base.adapter

interface ListItem {
    val listID: String?
    override fun equals(other: Any?): Boolean
}