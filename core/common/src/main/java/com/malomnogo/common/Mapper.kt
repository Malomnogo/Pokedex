package com.malomnogo.common

fun interface Mapper<I, O> {
    fun map(input: I): O
}
