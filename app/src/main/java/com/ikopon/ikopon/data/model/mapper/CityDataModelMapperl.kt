package com.ikopon.ikopon.data.model.mapper

import com.ikopon.ikopon.core.abstraction.entity.EntityMapper
import com.ikopon.ikopon.data.model.CityDataModel
import com.ikopon.ikopon.domain.entities.City
import javax.inject.Inject

class CityDataModelMapper @Inject constructor() :
    EntityMapper<City, CityDataModel> {

    override fun mapFromEntity(entity: City): CityDataModel =
        CityDataModel(
            entity.name, entity.citySlug,
            entity.tell,
            entity.weblink,
            entity.address,
            entity.location,
        )


    override fun mapToEntity(dataNodel: CityDataModel): City =
        City(
            dataNodel.name,
            dataNodel.citySlug,
            dataNodel.tell,
            dataNodel.weblink,
            dataNodel.address,
            dataNodel.location,
        )
}