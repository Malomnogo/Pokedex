package com.malomnogo.ui.di

import com.malomnogo.GeneratePokemonImageUrl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreUiModule = module {
    singleOf(GeneratePokemonImageUrl::DreamWorld) { bind<GeneratePokemonImageUrl>() }
}
