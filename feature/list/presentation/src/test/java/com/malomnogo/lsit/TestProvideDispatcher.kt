package com.malomnogo.lsit

import kotlinx.coroutines.test.TestDispatcher

class TestProvideDispatcher(
    testDispatcher: TestDispatcher
) : ProvideDispatcher {

    override val io = testDispatcher
    override val main = testDispatcher
    override val default = testDispatcher
}
