package com.malomnogo.lsit.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.malomnogo.lsit.presentation.PokemonItemMapper
import com.malomnogo.lsit.presentation.PokemonListViewModel
import com.malomnogo.ui.di.DaggerViewModelFactory
import com.malomnogo.ui.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FeatureListPresentationModule {
    @Binds
    fun bindPokemonItemMapper(impl: PokemonItemMapper.Base): PokemonItemMapper

    @Binds
    @IntoMap
    @ViewModelKey(PokemonListViewModel::class)
    fun bindPokemonListViewModel(viewModel: PokemonListViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}
