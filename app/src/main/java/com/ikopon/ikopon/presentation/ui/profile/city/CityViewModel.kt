package com.ikopon.ikopon.presentation.ui.profile.city;

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ikopon.ikopon.data.repository.CityRepository
import com.ikopon.ikopon.data.repository.base.AnyParam
import com.ikopon.ikopon.data.repository.base.ApiCallState
import com.ikopon.ikopon.data.repository.base.RepositoryStrategy
import com.ikopon.ikopon.model.City
import com.ikopon.ikopon.model.base.ApiListResponse
import com.ikopon.ikopon.presentation.base.BaseViewModel
import com.ikopon.ikopon.presentation.base.ViewState
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart


@ExperimentalCoroutinesApi
@SuppressLint("CheckResult")
class CityViewModel constructor(
    private val cityRepository: CityRepository,
) : BaseViewModel() {


    private val _cityListLiveData = MutableLiveData<ViewState<List<City>>>()
    val cityListLiveData: LiveData<ViewState<List<City>>>
        get() = _cityListLiveData


    fun getCityList() {
        cityRepository.getResult(AnyParam(), RepositoryStrategy.OfflineFirst)
            .flowOn(Dispatchers.IO)
            .onStart {
                _cityListLiveData.value = ViewState.Loading
            }
            .onEach {
                when (it) {
                    is ApiCallState.Success -> _cityListLiveData.value = ViewState.Success(data = it.data.data)
                    is ApiCallState.Failure -> _cityListLiveData.value = ViewState.Failure(error = it.error)
                }
            }
            .launchIn(viewModelScope)

    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}