package com.debin.challengechip.breeds.data.datasource

import com.debin.challengechip.breeds.data.model.DogBreedEntity
import com.debin.challengechip.breeds.domain.Breed
import com.debin.challengechip.breeds.domain.DogBreed
import io.reactivex.Single

interface IBreedsDataSource {
    fun getBreedsAsync() : Single<DogBreedEntity>
}