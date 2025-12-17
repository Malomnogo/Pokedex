package com.malomnogo.data

import com.malomnogo.common.HandleError
import com.malomnogo.common.ProvideResources
import com.malomnogo.network.model.PokemonErrorResponse
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseHandleError(
    private val provideResources: ProvideResources,
    private val json: Json = Json { ignoreUnknownKeys = true }
) : HandleError {

    override fun handle(e: Throwable): String {
        return when (e) {
            is HttpException -> handleHttpError(e)
            is UnknownHostException -> provideResources.noInternetConnectionMessage()
            else -> e.message ?: provideResources.serviceUnavailableMessage()
        }
    }

    private fun handleHttpError(e: HttpException): String {
        return try {
            val errorBody = e.response()?.errorBody()?.string()
            if (errorBody != null) {
                val errorResponse = json.decodeFromString<PokemonErrorResponse>(errorBody)
                errorResponse.message ?: provideResources.serverErrorCode(e.code())
            } else
                provideResources.serverErrorCode(e.code())
        } catch (_: Exception) {
            provideResources.errorParsingServerResponse()
        }
    }
}
