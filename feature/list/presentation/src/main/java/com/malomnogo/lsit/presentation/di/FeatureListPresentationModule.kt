package com.malomnogo.lsit.presentation.di

import com.malomnogo.lsit.presentation.PokemonItemMapper
import com.malomnogo.lsit.presentation.PokemonListStateMapper
import com.malomnogo.lsit.presentation.PokemonListViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureListPresentationModule =
    module {
        singleOf(PokemonItemMapper::Base) { bind<PokemonItemMapper>() }
        singleOf(PokemonListStateMapper::Base) { bind<PokemonListStateMapper>() }
        viewModelOf(::PokemonListViewModel)
    }
