package com.malomnogo.data

import com.malomnogo.common.HandleError
import com.malomnogo.common.ProvideResources
import com.malomnogo.model.core.PokemonErrorResponse
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BaseHandleError @Inject constructor(
    private val provideResources: ProvideResources,
    private val json: Json,
) : HandleError {
    override fun handle(e: Throwable): String =
        when (e) {
            is HttpException -> handleHttpError(e)
            is IOException -> provideResources.noInternetConnectionMessage()
            else -> e.message ?: provideResources.serviceUnavailableMessage()
        }

    private fun handleHttpError(e: HttpException): String =
        try {
            val errorBody = e.response()?.errorBody()?.string()
            if (!errorBody.isNullOrBlank()) {
                val errorResponse = json.decodeFromString<PokemonErrorResponse>(errorBody)
                errorResponse.message ?: provideResources.serverErrorCode(e.code())
            } else {
                provideResources.serverErrorCode(e.code())
            }
        } catch (_: Exception) {
            provideResources.errorParsingServerResponse()
        }
}
