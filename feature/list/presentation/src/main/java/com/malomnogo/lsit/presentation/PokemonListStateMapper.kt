package com.malomnogo.lsit.presentation

import com.malomnogo.domain.PokemonListResult

interface PokemonListStateMapper {
    fun map(result: PokemonListResult): PokemonListUiState

    class Base(
        private val itemMapper: PokemonItemMapper
    ) : PokemonListStateMapper {

        override fun map(result: PokemonListResult): PokemonListUiState {
            return when (result) {
                is PokemonListResult.Success -> {
                    PokemonListUiState.Base(
                        pokemonList = result.pokemonList.map { itemMapper.map(it) }
                    )
                }
                is PokemonListResult.Error -> {
                    PokemonListUiState.Error(message = result.message)
                }
            }
        }
    }
}
