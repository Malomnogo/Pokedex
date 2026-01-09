package com.malomnogo.pokedex.di

import android.app.Application
import android.content.Context
import com.malomnogo.common.ProvideResources
import com.malomnogo.pokedex.BaseProvideResources
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AppModule {
    @Binds
    fun bindContext(application: Application): Context

    @Binds
    @Singleton
    fun bindProvideResources(impl: BaseProvideResources): ProvideResources
}
