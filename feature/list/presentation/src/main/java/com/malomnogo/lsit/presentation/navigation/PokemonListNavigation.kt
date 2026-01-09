package com.malomnogo.lsit.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.malomnogo.list.api.PokemonListNavKey
import com.malomnogo.lsit.presentation.PokemonListScreen

fun pokemonListEntry(
    key: PokemonListNavKey,
    onPokemonClick: (Int) -> Unit,
    modifier: Modifier = Modifier
): NavEntry<NavKey> {
    return NavEntry(key) {
        PokemonListScreen(
            onPokemonClick = onPokemonClick,
            modifier = modifier
        )
    }
}
