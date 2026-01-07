package com.malomnogo.pokedex

import android.app.Application
import com.malomnogo.common.ProvideResources

class BaseProvideResources(
    private val context: Application,
) : ProvideResources {
    override fun noInternetConnectionMessage() = context.getString(R.string.no_internet_connection)

    override fun serviceUnavailableMessage() = context.getString(R.string.service_unavailable)

    override fun errorParsingServerResponse() = context.getString(R.string.error_parsing_server_response)

    override fun serverErrorCode(code: Int) = context.getString(R.string.server_error_code, code)
}
