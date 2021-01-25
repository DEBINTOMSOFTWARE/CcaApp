package com.debin.challengechip.breeds.data.datasource

import com.debin.challengechip.breeds.data.model.DogEntity
import com.debin.challengechip.breeds.domain.Dog
import io.reactivex.Single

interface IDogsDataSource {
    fun getDogAsync(breedName : String?) : Single<DogEntity>
}