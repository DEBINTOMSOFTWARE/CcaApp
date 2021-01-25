package com.debin.challengechip.framework.network

import com.debin.challengechip.breeds.data.model.DogBreedEntity
import com.debin.challengechip.breeds.data.model.DogEntity
import com.debin.challengechip.breeds.domain.Dog
import com.debin.challengechip.breeds.domain.DogBreed
import com.debin.challengechip.framework.utils.GET_BREEDS
import com.debin.challengechip.framework.utils.URL_DOG
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
 @GET(GET_BREEDS)
 fun getBreeds() : Single<DogBreedEntity>

 @GET("$URL_DOG/{breedName}/images")
 fun getDogs(@Path("breedName") breedName : String?) : Single<DogEntity>
}