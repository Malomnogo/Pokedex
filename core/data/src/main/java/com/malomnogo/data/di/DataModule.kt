package com.malomnogo.data.di

import com.malomnogo.common.HandleError
import com.malomnogo.data.BaseHandleError
import com.malomnogo.data.PokemonCloudDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {
    
    @Binds
    @Singleton
    fun bindPokemonCloudDataSource(impl: PokemonCloudDataSource.Base): PokemonCloudDataSource

    @Binds
    @Singleton
    fun bindHandleError(impl: BaseHandleError): HandleError
}
