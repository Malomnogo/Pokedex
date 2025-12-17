package com.malomnogo.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonErrorResponse(
    @SerialName("code")
    val code: Int? = null,
    @SerialName("message")
    val message: String? = null
)
