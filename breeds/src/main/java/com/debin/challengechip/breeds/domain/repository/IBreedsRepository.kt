package com.debin.challengechip.breeds.domain.repository

import com.debin.challengechip.breeds.domain.DogBreed
import io.reactivex.Single

interface IBreedsRepository {
 fun getBreeds() : Single<DogBreed>
}