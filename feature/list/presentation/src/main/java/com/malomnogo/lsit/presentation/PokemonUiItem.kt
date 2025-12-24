package com.malomnogo.lsit.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malomnogo.ui.theme.PokedexTheme

data class PokemonUiItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
)

@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    item: PokemonUiItem,
) {
    Column(modifier = modifier.padding(16.dp)) {
        PokemonListItemImage(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
            pokemon = item,
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "${item.name}#${item.id}",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonItemPreview() {
    val mock =
        PokemonUiItem(
            id = 1,
            name = "Bulbasaur",
            imageUrl = "",
        )
    PokedexTheme {
        PokemonItem(item = mock)
    }
}
