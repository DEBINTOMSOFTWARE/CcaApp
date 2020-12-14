package com.debin.challengechip.di

import com.debin.challengechip.presentation.breeds.BreedsViewModel
import com.debin.challengechip.presentation.dogs.DogsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
        viewModel { BreedsViewModel(get()) }
        viewModel { DogsViewModel(get()) }
}