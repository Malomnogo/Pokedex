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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PokemonListContent(
        modifier = modifier,
        uiState = uiState,
        onIntent = viewModel::onIntent
    )
}

@Composable
private fun PokemonListContent(
    modifier: Modifier,
    uiState: PokemonListUiState,
    onIntent: (PokemonListIntent) -> Unit
) {
    when (uiState) {
        PokemonListUiState.Progress -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        }

        is PokemonListUiState.Base -> {
            LazyVerticalGrid(
                modifier = modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
            ) {
                items(uiState.pokemonList) { pokemonUi ->
                    PokemonItem(item = pokemonUi, modifier = Modifier)
                }
            }
        }

        is PokemonListUiState.Error -> {
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

@Preview(showBackground = true, name = "Loading State")
@Composable
private fun PreviewPokemonList_Loading() {
    PokemonListContent(
        modifier = Modifier.fillMaxSize(),
        uiState = PokemonListUiState.Progress,
        onIntent = {}
    )
}

@Preview(showBackground = true, name = "Error State")
@Composable
private fun PreviewPokemonList_Error() {
    PokemonListContent(
        modifier = Modifier.fillMaxSize(),
        uiState = PokemonListUiState.Error("Something went wrong"),
        onIntent = {}
    )
}

@Preview(showBackground = true, name = "Success State")
@Composable
private fun PreviewPokemonList_Success() {
    PokemonListContent(
        modifier = Modifier.fillMaxSize(),
        uiState = PokemonListUiState.Base(
            pokemonList = listOf(
                PokemonUiItem(1, "Bulbasaur", ""),
                PokemonUiItem(4, "Charmander", ""),
                PokemonUiItem(7, "Squirtle", ""),
                PokemonUiItem(25, "Pikachu", "")
            )
        ),
        onIntent = {}
    )
}
