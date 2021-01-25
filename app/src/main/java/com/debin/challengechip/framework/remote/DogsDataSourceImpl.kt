package com.debin.challengechip.framework.remote

import com.debin.challengechip.breeds.data.datasource.IDogsDataSource
import com.debin.challengechip.breeds.data.model.DogEntity
import com.debin.challengechip.breeds.domain.Dog
import com.debin.challengechip.framework.network.ApiService
import io.reactivex.Single

class DogsDataSourceImpl(private val apiService: ApiService) : IDogsDataSource {
    override fun getDogAsync(breedName: String?): Single<DogEntity> {
        return apiService.getDogs(breedName)
    }
}