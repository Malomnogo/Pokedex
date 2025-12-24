package com.malomnogo.list.data

import com.malomnogo.domain.PokemonDomain
import com.malomnogo.model.PokemonCloud

sealed class PokemonCloudMapper<T> {
    fun map(input: PokemonCloud): T =
        build(
            id = input.id,
            name = normalizeName(input.name),
        )

    protected abstract fun build(
        id: Int,
        name: String,
    ): T

    protected fun normalizeName(name: String): String = name.replaceFirstChar { it.uppercase() }

    object ToDomain : PokemonCloudMapper<PokemonDomain>() {
        override fun build(
            id: Int,
            name: String,
        ) = PokemonDomain(
            id = id,
            name = name,
        )
    }
}
