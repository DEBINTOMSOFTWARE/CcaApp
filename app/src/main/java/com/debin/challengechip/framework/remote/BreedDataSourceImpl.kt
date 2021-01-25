package com.debin.challengechip.framework.remote

import com.debin.challengechip.breeds.data.datasource.IBreedsDataSource
import com.debin.challengechip.breeds.data.model.DogBreedEntity
import com.debin.challengechip.breeds.domain.DogBreed
import com.debin.challengechip.framework.network.ApiService
import io.reactivex.Single

class BreedDataSourceImpl(private val apiService: ApiService) : IBreedsDataSource {
    override fun getBreedsAsync(): Single<DogBreedEntity> {
        return apiService.getBreeds()
    }
}