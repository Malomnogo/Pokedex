package com.malomnogo.domain

interface PokemonListRepository {
    suspend fun fetchPokemonList(): PokemonListResult
}
