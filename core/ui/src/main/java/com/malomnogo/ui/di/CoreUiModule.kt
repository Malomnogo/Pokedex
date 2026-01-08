package com.malomnogo.ui.di

import com.malomnogo.GeneratePokemonImageUrl
import com.malomnogo.GeneratePokemonNumber
import dagger.Binds
import dagger.Module

@Module
interface CoreUiModule {

    @Binds
    fun bindGeneratePokemonImageUrl(impl: GeneratePokemonImageUrl.DreamWorld): GeneratePokemonImageUrl

    @Binds
    fun bindGeneratePokemonNumber(impl: GeneratePokemonNumber.Base): GeneratePokemonNumber
}
