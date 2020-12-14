package com.debin.challengechip.breeds.domain.repository

import com.debin.challengechip.breeds.domain.Dog
import io.reactivex.Single

interface IDogsRepository {
    fun getDogs(breedName : String?) : Single<Dog>
}