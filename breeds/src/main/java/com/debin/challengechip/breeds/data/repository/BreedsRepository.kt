package com.debin.challengechip.breeds.data.repository

import com.debin.challengechip.breeds.data.datasource.IBreedsDataSource
import com.debin.challengechip.breeds.data.mappers.dogbreedmapper.DogBreedEntityMapper
import com.debin.challengechip.breeds.domain.DogBreed
import com.debin.challengechip.breeds.domain.repository.IBreedsRepository
import com.debin.challengechip.breeds.domain.utils.OpenForTesting
import io.reactivex.Single
@OpenForTesting
class BreedsRepository(private val dataSource: IBreedsDataSource,
                       private val entityMapper: DogBreedEntityMapper) : IBreedsRepository {
    override fun getBreeds(): Single<DogBreed> {
        return dataSource.getBreedsAsync().map {
            entityMapper.mapFromRemote(it)
        }
    }

}