package com.ikopon.ikopon.presentation.ui.profile.city

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ikopon.ikopon.presentation.view.binding.viewBindingDelegate.viewBinding
import com.ikopon.ikopon.databinding.ActivityCityListBinding
import com.ikopon.ikopon.model.City
import com.ikopon.ikopon.presentation.base.BaseActivity
import com.ikopon.ikopon.presentation.base.ViewState
import com.ikopon.ikopon.presentation.ui.profile.city.adapter.CityAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class CityListActivity : BaseActivity<ActivityCityListBinding>() {

    override val binding: ActivityCityListBinding by viewBinding(ActivityCityListBinding::inflate)

    private val viewModel by viewModel<CityViewModel>()

    private var cityList: ArrayList<City> = arrayListOf()

    companion object {

        fun start(context: Context) {
            Intent(context, CityListActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeApiCallListener(viewModel)
        val meetingAdapter = CityAdapter(cityList, this, ::onItemClick)
        binding.rvRooms.layoutManager = LinearLayoutManager(this)
        binding.rvRooms.adapter = meetingAdapter

        viewModel.cityListLiveData.observe(this, {
            when (it) {
                is ViewState.Success -> {
                    binding.loading.hideProgress()
                    cityList.clear()
                    cityList.addAll(it.data)
                    meetingAdapter.notifyDataSetChanged()
                }
                is ViewState.Failure -> {
                    binding.loading.error()
                }
                is ViewState.Loading -> {
                    binding.loading.loading()
                }
            }
        })

        viewModel.getCityList()

    }


    private fun onItemClick(position: Int, view: Int?) {
        if (cityList.size > position) {
            val city = cityList[position]

        }
    }
}