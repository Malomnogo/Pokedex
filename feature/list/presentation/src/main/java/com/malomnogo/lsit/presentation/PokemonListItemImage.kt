package com.malomnogo.lsit.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import com.malomnogo.ui.WebImage

@Composable
internal fun PokemonListItemImage(
    pokemon: PokemonUiItem,
    modifier: Modifier = Modifier,
) {
    val loadingPainter = remember { ColorPainter(Color.Gray) }
    val errorPainter = remember { ColorPainter(Color.Red) }

    WebImage(
        url = pokemon.imageUrl,
        modifier = modifier,
        contentDescription = pokemon.name,
        loadingPlaceholder = loadingPainter,
        errorPlaceholder = errorPainter,
    )
}
