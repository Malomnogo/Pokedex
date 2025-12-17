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
        viewModel: PokemonListViewModel,
    )

    object FirstRun : PokemonListUiState {
        @Composable
        override fun Show(
            modifier: Modifier,
            viewModel: PokemonListViewModel,
        ) {
            LaunchedEffect(Unit) {
                viewModel.loadData()
            }
        }
    }

    data class Base(
        private val pokemonList: List<PokemonUiItem>,
    ) : PokemonListUiState {
        @Composable
        override fun Show(
            modifier: Modifier,
            viewModel: PokemonListViewModel,
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(pokemonList) { pokemonUi ->
                    pokemonUi.Show(modifier = Modifier)
                }
            }
        }
    }

    object Progress : PokemonListUiState {
        @Composable
        override fun Show(
            modifier: Modifier,
            viewModel: PokemonListViewModel,
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
            viewModel: PokemonListViewModel,
        ) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Button(onClick = {
                    viewModel.loadData()
                }) {
                    Text(text = "Retry")
                }
            }
        }
    }
}
