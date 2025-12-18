package com.malomnogo.common

interface HandleError {
    fun handle(e: Throwable): String
}
