package com.malomnogo.list.data.di

import com.malomnogo.domain.PokemonDomain
import com.malomnogo.domain.PokemonListRepository
import com.malomnogo.list.data.BasePokemonListRepository
import com.malomnogo.list.data.PokemonCloudMapper
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface FeatureListDataModule {
    @Binds
    fun bindPokemonListRepository(impl: BasePokemonListRepository): PokemonListRepository

    companion object {
        @Provides
        fun providePokemonCloudMapper(): PokemonCloudMapper<PokemonDomain> {
            return PokemonCloudMapper.ToDomain
        }
    }
}
