package com.malomnogo.lsit.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malomnogo.ui.theme.PokedexTheme

data class PokemonUiItem(
    val id: Int,
    val number: String,
    val name: String,
    val imageUrl: String,
)

@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    item: PokemonUiItem,
    onClick: (id: Int) -> Unit
) {
    Card(
        modifier = modifier.aspectRatio(1f),
        onClick = {
            onClick.invoke(item.id)
        }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 4.dp, end = 8.dp),
                style = MaterialTheme.typography.titleSmall,
                text = item.number,
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                PokemonListItemImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    pokemon = item,
                )
            }

            Text(
                modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                text = item.name,
                maxLines = 1,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonItemPreview() {
    val mock = PokemonUiItem(
        id = 1,
        name = "Bulbasaur",
        number = "#001",
        imageUrl = "",
    )
    PokedexTheme {
        PokemonItem(
            item = mock,
            onClick = {}
        )
    }
}
