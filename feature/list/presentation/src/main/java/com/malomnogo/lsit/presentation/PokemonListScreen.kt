package com.malomnogo.lsit.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    uiState.Show(modifier = modifier, viewModel = viewModel)
}
