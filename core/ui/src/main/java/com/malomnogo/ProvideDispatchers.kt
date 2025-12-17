package com.malomnogo

import kotlinx.coroutines.CoroutineDispatcher

interface ProvideDispatchers {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
}
