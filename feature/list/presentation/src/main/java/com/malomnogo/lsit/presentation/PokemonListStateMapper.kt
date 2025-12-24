package com.malomnogo.lsit.presentation

import com.malomnogo.domain.PokemonListResult

interface PokemonListStateMapper {
    fun map(input: PokemonListResult): PokemonListUiState

    class Base(
        private val itemMapper: PokemonItemMapper,
    ) : PokemonListStateMapper {
        override fun map(input: PokemonListResult): PokemonListUiState =
            when (input) {
                is PokemonListResult.Success -> {
                    PokemonListUiState.Base(
                        pokemonList = input.pokemonList.map { itemMapper.map(it) },
                    )
                }
                is PokemonListResult.Error -> {
                    PokemonListUiState.Error(message = input.message)
                }
            }
    }
}
