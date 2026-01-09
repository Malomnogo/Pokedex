package com.malomnogo.common.di

import com.malomnogo.common.AppDispatchers
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class CommonModule {
    @Provides
    @Singleton
    fun provideAppDispatchers(): AppDispatchers {
        return object : AppDispatchers {
            override val io: CoroutineDispatcher = Dispatchers.IO
            override val main: CoroutineDispatcher = Dispatchers.Main
            override val default: CoroutineDispatcher = Dispatchers.Default
        }
    }
}
