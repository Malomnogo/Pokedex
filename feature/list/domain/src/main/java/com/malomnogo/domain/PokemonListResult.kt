package com.malomnogo.domain

sealed interface PokemonListResult {
    data class Success(
        val pokemonList: List<PokemonDomain>,
    ) : PokemonListResult

    data class Error(
        val message: String,
    ) : PokemonListResult
}
