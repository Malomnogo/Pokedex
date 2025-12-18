package com.malomnogo.common.di

import com.malomnogo.common.AppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val commonModule = module {
    single<AppDispatchers> {
        object : AppDispatchers {
            override val io: CoroutineDispatcher = Dispatchers.IO
            override val main: CoroutineDispatcher = Dispatchers.Main
            override val default: CoroutineDispatcher = Dispatchers.Default
        }
    }
}
