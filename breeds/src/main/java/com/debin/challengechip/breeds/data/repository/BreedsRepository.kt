package com.debin.challengechip.breeds.data.repository

import com.debin.challengechip.breeds.data.datasource.IBreedsDataSource
import com.debin.challengechip.breeds.domain.DogBreed
import com.debin.challengechip.breeds.domain.repository.IBreedsRepository
import io.reactivex.Single

class BreedsRepository(private val dataSource: IBreedsDataSource) : IBreedsRepository {
    override fun getBreeds(): Single<DogBreed> {
        return dataSource.getBreedsAsync()
    }

}