package com.malomnogo.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface PokemonListRepository {
    fun fetchPokemonList(): Flow<PagingData<PokemonDomain>>
}
