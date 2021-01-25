package com.debin.challengechip.di

import com.debin.challengechip.breeds.data.mappers.dogbreedmapper.DogBreedEntityMapper
import com.debin.challengechip.breeds.data.mappers.dogmapper.DogEntityMapper
import org.koin.dsl.module

val mapperModule = module {
    single { DogBreedEntityMapper() }
    single { DogEntityMapper() }
}