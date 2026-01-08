package com.malomnogo.lsit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.malomnogo.domain.PokemonListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonListViewModel @Inject constructor(
    repository: PokemonListRepository,
    private val itemMapper: PokemonItemMapper,
) : ViewModel() {
    val pokemonList: Flow<PagingData<PokemonUiItem>> =
        repository
            .fetchPokemonList()
            .map { pagingData ->
                pagingData.map { itemMapper.map(it) }
            }.cachedIn(viewModelScope)
}
