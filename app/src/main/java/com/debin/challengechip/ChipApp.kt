package com.debin.challengechip

import android.app.Application
import com.debin.challengechip.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ChipApp : Application() {
    private val modules = listOf(
        apiModule,
        dataSourceModule,
        repositoryModule,
        useCasesModule,
        viewModelModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(modules)
        }
    }
}