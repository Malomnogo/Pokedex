package com.malomnogo.list.data.di

import com.malomnogo.domain.PokemonListRepository
import com.malomnogo.list.data.BasePokemonCloudMapper
import com.malomnogo.list.data.BasePokemonListRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val featureListDataModule = module {
    // Инжектим конкретный класс, чтобы избежать коллизий Generics
    singleOf(::BasePokemonCloudMapper) 
    singleOf(::BasePokemonListRepository) { bind<PokemonListRepository>() }
}
