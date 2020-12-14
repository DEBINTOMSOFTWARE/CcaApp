package com.debin.challengechip.di

import com.debin.challengechip.breeds.data.datasource.IBreedsDataSource
import com.debin.challengechip.breeds.data.datasource.IDogsDataSource
import com.debin.challengechip.framework.remote.BreedDataSourceImpl
import com.debin.challengechip.framework.remote.DogsDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<IBreedsDataSource> { BreedDataSourceImpl(get()) }
    single<IDogsDataSource> { DogsDataSourceImpl(get()) }
}