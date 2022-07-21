package com.ikopon.ikopon.presentation.ui.profile.city.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ikopon.ikopon.R
import com.ikopon.ikopon.databinding.ItemCityBinding
import com.ikopon.ikopon.domain.entities.City

class CityAdapter (private val properties: List<City>, val context: Context, private val adapterCallBack: (position: Int, view: Int?) -> Unit) :
    RecyclerView.Adapter<CityListItemViewHolder>() {

    override fun getItemCount(): Int {
        return properties.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListItemViewHolder {
        val view: ItemCityBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_city, parent, false)
        return CityListItemViewHolder(view, context, adapterCallBack)
    }

//    private fun adapterCallBack(position: Int, view: Int?) {
//
//    }

    override fun onBindViewHolder(holder: CityListItemViewHolder, position: Int) {
        holder.bind(properties[position])
    }
}