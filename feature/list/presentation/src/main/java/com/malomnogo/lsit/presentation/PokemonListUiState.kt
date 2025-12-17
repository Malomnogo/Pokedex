package com.malomnogo.lsit.presentation


interface PokemonListUiState {

    object Progress : PokemonListUiState

    data class Base(private val pokemonList: List<PokemonUiItem>) : PokemonListUiState

    data class Error(private val message: String) : PokemonListUiState

    object Empty : PokemonListUiState
}
