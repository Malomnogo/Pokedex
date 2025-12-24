package com.malomnogo.list.data.di

import com.malomnogo.domain.PokemonDomain
import com.malomnogo.domain.PokemonListRepository
import com.malomnogo.list.data.BasePokemonListRepository
import com.malomnogo.list.data.PokemonCloudMapper
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val featureListDataModule = module {
    single<PokemonCloudMapper<PokemonDomain>> {
        PokemonCloudMapper.ToDomain
    }
    singleOf(::BasePokemonListRepository) { bind<PokemonListRepository>() }
}
