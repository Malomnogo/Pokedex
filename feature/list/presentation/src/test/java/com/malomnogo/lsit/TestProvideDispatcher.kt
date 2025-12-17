package com.malomnogo.lsit

import com.malomnogo.ProvideDispatchers
import kotlinx.coroutines.test.TestDispatcher

class TestProvideDispatcher(
    testDispatcher: TestDispatcher
) : ProvideDispatchers {

    override val io = testDispatcher
    override val main = testDispatcher
    override val default = testDispatcher
}
