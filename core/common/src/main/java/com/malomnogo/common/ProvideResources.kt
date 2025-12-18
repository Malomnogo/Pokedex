package com.malomnogo.common

interface ProvideResources {
    fun noInternetConnectionMessage(): String
    fun serviceUnavailableMessage(): String
    fun errorParsingServerResponse(): String
    fun serverErrorCode(code: Int): String
}
