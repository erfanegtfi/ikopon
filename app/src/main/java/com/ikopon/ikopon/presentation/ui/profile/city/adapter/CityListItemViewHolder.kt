package com.ikopon.ikopon.presentation.ui.profile.city.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ikopon.ikopon.databinding.ItemCityBinding
import com.ikopon.ikopon.model.City

class CityListItemViewHolder(
    private val binding: ItemCityBinding,
    private val context: Context,
    private val adapterCallBack: (position: Int, view: Int?) -> Unit,
) : RecyclerView.ViewHolder(
    binding.root
), View.OnClickListener {

    fun bind(model: City) {
        binding.keyValue = model
        binding.container.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null)
            adapterCallBack(adapterPosition, v.id)
    }
}