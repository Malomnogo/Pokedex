package com.malomnogo.lsit.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.malomnogo.list.api.ListFeatureDependencies
import com.malomnogo.lsit.presentation.di.DaggerListComponent

@Composable
fun PokemonListScreen(
    onPokemonClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val dependencies = (context.applicationContext as ListFeatureDependencies)
    
    val component = remember(dependencies) {
        DaggerListComponent.factory().create(dependencies)
    }
    
    val viewModel: PokemonListViewModel = viewModel(factory = component.viewModelFactory())
    val pokemonList = viewModel.pokemonList.collectAsLazyPagingItems()

    PokemonListContent(
        modifier = modifier,
        pokemonList = pokemonList,
        onPokemonClick = onPokemonClick,
    )
}

@Composable
private fun PokemonListContent(
    modifier: Modifier,
    pokemonList: LazyPagingItems<PokemonUiItem>,
    onPokemonClick: (Int) -> Unit,
) {
    when (val refreshState = pokemonList.loadState.refresh) {
        is LoadState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        }

        is LoadState.Error -> {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = refreshState.error.localizedMessage ?: "Unknown error",
                    textAlign = TextAlign.Center,
                )
                Button(onClick = { pokemonList.retry() }) {
                    Text(text = "Retry")
                }
            }
        }

        else -> {
            LazyVerticalGrid(
                modifier = modifier.fillMaxSize(),
                columns = GridCells.Adaptive(minSize = 105.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(
                    count = pokemonList.itemCount,
                    // Для ключа используем peek, чтобы не триггерить загрузку лишний раз при расчете ключей
                    key = { index -> pokemonList.peek(index)?.id ?: index },
                ) { index ->
                    // ВАЖНО: Здесь используем [] (get), чтобы библиотека поняла, что элемент отображен
                    // и нужно подгружать следующую страницу
                    val item = pokemonList[index]

                    if (item != null) {
                        PokemonItem(
                            item = item,
                            onClick = onPokemonClick,
                        )
                    }
                }

                when (pokemonList.loadState.append) {
                    is LoadState.Loading -> {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Box(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    is LoadState.Error -> {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Column(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text("Error loading more items")
                                Button(onClick = { pokemonList.retry() }) {
                                    Text("Retry")
                                }
                            }
                        }
                    }

                    is LoadState.NotLoading -> Unit
                }
            }
        }
    }
}
