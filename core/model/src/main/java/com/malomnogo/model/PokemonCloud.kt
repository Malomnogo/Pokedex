package com.malomnogo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonCloud(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
) {
    val id: Int
        get() = url.trimEnd('/').substringAfterLast('/').toIntOrNull() ?: 0
}
