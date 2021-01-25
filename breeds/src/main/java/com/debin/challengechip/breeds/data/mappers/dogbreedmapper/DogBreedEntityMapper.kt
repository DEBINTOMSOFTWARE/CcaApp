package com.debin.challengechip.breeds.data.mappers.dogbreedmapper

import com.debin.challengechip.breeds.data.mappers.EntityMapper
import com.debin.challengechip.breeds.data.model.DogBreedEntity
import com.debin.challengechip.breeds.domain.DogBreed
import com.debin.challengechip.breeds.domain.utils.OpenForTesting

@OpenForTesting
open class DogBreedEntityMapper : EntityMapper<DogBreedEntity, DogBreed>() {
    override fun mapFromRemote(type: DogBreedEntity): DogBreed {
        return DogBreed(type.message, type.status)
    }
}