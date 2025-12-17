package com.malomnogo.lsit.presentation.di

import com.malomnogo.common.Mapper
import com.malomnogo.domain.PokemonDomain
import com.malomnogo.lsit.presentation.PokemonItemMapper
import com.malomnogo.lsit.presentation.PokemonListStateMapper
import com.malomnogo.lsit.presentation.PokemonListViewModel
import com.malomnogo.lsit.presentation.PokemonUiItem
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureListPresentationModule = module {
    // Mappers
    singleOf(::PokemonItemMapper) { bind<Mapper<PokemonDomain, PokemonUiItem>>() }
    singleOf(PokemonListStateMapper::Base) { bind<PokemonListStateMapper>() }

    // ViewModel
    viewModelOf(::PokemonListViewModel)
}
