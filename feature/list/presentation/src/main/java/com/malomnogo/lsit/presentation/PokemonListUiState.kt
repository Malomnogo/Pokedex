package com.malomnogo.lsit.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

interface PokemonListUiState {
    @Composable
    fun Show(
        modifier: Modifier,
        onIntent: (PokemonListIntent) -> Unit,
    )

    object FirstRun : PokemonListUiState {
        @Composable
        override fun Show(
            modifier: Modifier,
            onIntent: (PokemonListIntent) -> Unit,
        ) {
            LaunchedEffect(Unit) {
                onIntent(PokemonListIntent.LoadData)
            }
        }
    }

    data class Base(
        private val pokemonList: List<PokemonUiItem>,
    ) : PokemonListUiState {
        @Composable
        override fun Show(
            modifier: Modifier,
            onIntent: (PokemonListIntent) -> Unit,
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(pokemonList) { pokemonUi ->
                    PokemonItem(item = pokemonUi, modifier = Modifier)
                }
            }
        }
    }

    object Progress : PokemonListUiState {
        @Composable
        override fun Show(
            modifier: Modifier,
            onIntent: (PokemonListIntent) -> Unit,
        ) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(modifier)
            }
        }
    }

    data class Error(
        private val message: String,
    ) : PokemonListUiState {
        @Composable
        override fun Show(
            modifier: Modifier,
            onIntent: (PokemonListIntent) -> Unit,
        ) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Button(onClick = {
                    onIntent(PokemonListIntent.Retry)
                }) {
                    Text(text = "Retry")
                }
            }
        }
    }
}
