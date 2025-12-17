package com.malomnogo.lsit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malomnogo.ProvideDispatchers
import com.malomnogo.domain.PokemonListResult
import com.malomnogo.domain.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonListViewModel(
    private val repository: PokemonRepository,
    private val mapper: PokemonListResult.Mapper<PokemonListUiState>,
    private val provideDispatchers: ProvideDispatchers
) : ViewModel() {

    private val _uiState = MutableStateFlow<PokemonListUiState>(PokemonListUiState.Empty)
    val uiState = _uiState.asStateFlow()

    fun loadData() {
        _uiState.value = PokemonListUiState.Progress
        viewModelScope.launch(provideDispatchers.io) {
            val result = repository.fetchPokemonList()
            withContext(provideDispatchers.main) {
                _uiState.value = result.map(mapper)
            }
        }
    }
}
