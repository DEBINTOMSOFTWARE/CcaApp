package com.debin.challengechip.di

import com.debin.challengechip.breeds.data.repository.BreedsRepository
import com.debin.challengechip.breeds.data.repository.DogsRepository
import com.debin.challengechip.breeds.domain.repository.IBreedsRepository
import com.debin.challengechip.breeds.domain.repository.IDogsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IBreedsRepository> { BreedsRepository(get(), get()) }
    single<IDogsRepository> { DogsRepository(get(), get()) }
}