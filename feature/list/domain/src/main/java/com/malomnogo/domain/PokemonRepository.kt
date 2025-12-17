package com.malomnogo.domain

interface PokemonRepository {
    suspend fun fetchPokemonList(): PokemonListResult
}
