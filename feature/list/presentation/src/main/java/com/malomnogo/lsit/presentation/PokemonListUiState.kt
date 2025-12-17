package com.malomnogo.lsit.presentation

sealed interface PokemonListUiState {
    data object Progress : PokemonListUiState
    data class Base(val pokemonList: List<PokemonUiItem>) : PokemonListUiState
    data class Error(val message: String) : PokemonListUiState
}
