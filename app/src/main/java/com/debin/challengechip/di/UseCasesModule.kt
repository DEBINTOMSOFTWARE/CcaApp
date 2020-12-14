package com.debin.challengechip.di


import com.challenger.domain.executor.JobExecutor
import com.debin.challengechip.breeds.interactors.GetBreeds
import com.debin.challengechip.breeds.interactors.GetDogs
import com.debin.challengechip.framework.executor.UiThread
import org.koin.dsl.module

val useCasesModule = module {
    single { GetBreeds(get(), JobExecutor(), UiThread()) }
    factory { GetDogs(get(), JobExecutor(), UiThread()) }
}