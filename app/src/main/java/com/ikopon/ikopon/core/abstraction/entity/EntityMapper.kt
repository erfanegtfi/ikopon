package com.ikopon.ikopon.core.abstraction.entity

interface EntityMapper<Entity,DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel
    fun mapToEntity(domainModel: DomainModel): Entity
}