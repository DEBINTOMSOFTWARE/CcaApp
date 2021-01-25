package com.debin.challengechip.breeds.data.mappers.dogmapper

import com.debin.challengechip.breeds.data.mappers.EntityMapper
import com.debin.challengechip.breeds.data.model.DogEntity
import com.debin.challengechip.breeds.domain.Dog

open class DogEntityMapper : EntityMapper<DogEntity, Dog>() {
    override fun mapFromRemote(type: DogEntity): Dog {
        return Dog(type.message, type.status)
    }
}