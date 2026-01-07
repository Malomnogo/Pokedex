package com.malomnogo.data.di

import com.malomnogo.common.HandleError
import com.malomnogo.data.BaseHandleError
import com.malomnogo.data.PokemonCloudDataSource
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreDataModule =
    module {
        singleOf(PokemonCloudDataSource::Base) { bind<PokemonCloudDataSource>() }
        singleOf(::BaseHandleError) { bind<HandleError>() }
    }
