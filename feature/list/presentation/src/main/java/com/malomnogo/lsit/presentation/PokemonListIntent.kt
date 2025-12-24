package com.malomnogo.lsit.presentation

sealed interface PokemonListIntent {
    data object LoadData : PokemonListIntent

    data object Retry : PokemonListIntent
}
