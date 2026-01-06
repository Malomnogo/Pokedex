package com.malomnogo.data

import com.malomnogo.common.ProvideResources
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class BaseHandleErrorTest {
    private val provideResources = FakeProvideResources()
    private val json = Json { ignoreUnknownKeys = true }
    private val handleError = BaseHandleError(provideResources, json)

    @Test
    fun `handle returns no internet message when IOException occurs`() {
        val exception = IOException()

        val result = handleError.handle(exception)

        assertEquals("No internet", result)
    }

    @Test
    fun `handle returns parsed error message when HttpException has valid error body`() {
        val jsonBody = """{"message": "Pokemon not found"}"""
        val exception = httpException(404, jsonBody)

        val result = handleError.handle(exception)

        assertEquals("Pokemon not found", result)
    }

    @Test
    fun `handle returns server error code message when HttpException has empty body`() {
        val exception = httpException(500, "")

        val result = handleError.handle(exception)

        assertEquals("Error code: 500", result)
    }

    @Test
    fun `handle returns parsing error message when HttpException has invalid json`() {
        val invalidJson = """{invalid json}"""
        val exception = httpException(400, invalidJson)

        val result = handleError.handle(exception)

        assertEquals("Parsing error", result)
    }

    @Test
    fun `handle returns exception message when unknown error occurs`() {
        val message = "Something went wrong"
        val exception = RuntimeException(message)

        val result = handleError.handle(exception)

        assertEquals(message, result)
    }

    @Test
    fun `handle returns service unavailable message when unknown error has no message`() {
        val exception = RuntimeException()

        val result = handleError.handle(exception)

        assertEquals("Service unavailable", result)
    }

    private fun httpException(
        code: Int,
        body: String,
    ): HttpException =
        HttpException(
            Response.error<Any>(
                code,
                body.toResponseBody("application/json".toMediaTypeOrNull()),
            ),
        )

    private class FakeProvideResources : ProvideResources {
        override fun noInternetConnectionMessage(): String = "No internet"

        override fun serviceUnavailableMessage(): String = "Service unavailable"

        override fun errorParsingServerResponse(): String = "Parsing error"

        override fun serverErrorCode(code: Int): String = "Error code: $code"
    }
}
