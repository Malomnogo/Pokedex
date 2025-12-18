package com.malomnogo.lsit.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.malomnogo.lsit.presentation.PokemonListScreen
import kotlinx.serialization.Serializable

@Serializable
object PokemonListRoute

fun NavController.navigateToPokemonList(navOptions: NavOptions? = null) {
    navigate(route = PokemonListRoute, navOptions = navOptions)
}

fun NavGraphBuilder.pokemonListScreen(
    onPokemonClick: (Int) -> Unit
) {
    composable<PokemonListRoute> {
        PokemonListScreen(
            onPokemonClick = onPokemonClick
        )
    }
}
