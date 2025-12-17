package com.malomnogo.lsit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malomnogo.domain.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonListViewModel(
    private val repository: PokemonRepository,
    private val mapper: PokemonListStateMapper,
) : ViewModel() {
    private val _uiState = MutableStateFlow<PokemonListUiState>(PokemonListUiState.Progress)
    val uiState = _uiState.asStateFlow()

    init {
        loadData()
    }

    fun onIntent(intent: PokemonListIntent) {
        when (intent) {
            PokemonListIntent.LoadData -> loadData()
            PokemonListIntent.Retry -> loadData()
        }
    }

    private fun loadData() {
        _uiState.value = PokemonListUiState.Progress
        viewModelScope.launch {
            val result = repository.fetchPokemonList()
            withContext(Dispatchers.Main) {
                _uiState.value = mapper.map(result)
            }
        }
    }
}
