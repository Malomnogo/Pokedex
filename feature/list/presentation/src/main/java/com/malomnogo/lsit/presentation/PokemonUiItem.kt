package com.malomnogo.lsit.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class PokemonUiItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
)

@Composable
fun PokemonItem(
    item: PokemonUiItem,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(text = "${item.name}#${item.id}")
    }
}
