package com.malomnogo.pokedex

import android.app.Application
import com.malomnogo.common.ProvideResources
import com.malomnogo.common.di.commonModule
import com.malomnogo.data.di.coreDataModule
import com.malomnogo.list.data.di.featureListDataModule
import com.malomnogo.lsit.presentation.di.featureListPresentationModule
import com.malomnogo.network.di.networkModule
import com.malomnogo.ui.di.coreUiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class PokedexApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PokedexApp)
            modules(
                networkModule,
                commonModule,
                coreDataModule,
                coreUiModule, // Added
                featureListDataModule,
                featureListPresentationModule,
                appModule
            )
        }
    }
}

val appModule = module {
    single<ProvideResources> { BaseProvideResources(get()) }
}
