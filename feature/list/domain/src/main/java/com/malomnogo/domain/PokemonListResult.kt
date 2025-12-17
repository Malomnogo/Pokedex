package com.malomnogo.domain

interface PokemonListResult {
    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {
        fun mapSuccess(pokemonList: List<PokemonDomain>): T

        fun mapError(message: String): T
    }

    data class Success(
        private val pokemonList: List<PokemonDomain>,
    ) : PokemonListResult {
        override fun <T : Any> map(mapper: Mapper<T>) = mapper.mapSuccess(pokemonList = pokemonList)
    }

    data class Error(
        private val message: String,
    ) : PokemonListResult {
        override fun <T : Any> map(mapper: Mapper<T>) = mapper.mapError(message = message)
    }
}
