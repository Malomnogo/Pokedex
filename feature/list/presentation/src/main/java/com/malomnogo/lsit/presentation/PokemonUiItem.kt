package com.malomnogo.lsit.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class PokemonUiItem(
    private val id: Int,
    private val name: String,
    private val imageUrl: String,
) {
    @Composable
    fun Show(modifier: Modifier) {
        Column(modifier = modifier) {
            Text(text = "$name#$id")
        }
    }
}
