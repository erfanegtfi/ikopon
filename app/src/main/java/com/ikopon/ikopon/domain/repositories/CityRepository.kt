package com.ikopon.ikopon.domain.repositories

import com.ikopon.ikopon.core.utils.ApiCallState
import com.ikopon.ikopon.data.repository.base.RepositoryStrategy
import com.ikopon.ikopon.domain.entities.City
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    fun getCityList( strategy: RepositoryStrategy): Flow<ApiCallState<List<City>>>
}