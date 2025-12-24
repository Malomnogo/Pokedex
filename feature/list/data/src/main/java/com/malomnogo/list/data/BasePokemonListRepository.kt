package com.malomnogo.list.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.malomnogo.data.PokemonCloudDataSource
import com.malomnogo.domain.PokemonDomain
import com.malomnogo.domain.PokemonListRepository
import kotlinx.coroutines.flow.Flow

class BasePokemonListRepository(
    private val cloudDataSource: PokemonCloudDataSource,
    private val mapper: PokemonCloudMapper<PokemonDomain>,
) : PokemonListRepository {
    override fun fetchPokemonList(): Flow<PagingData<PokemonDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20, // Или другое значение, которое вам подходит
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PokemonPagingSource(cloudDataSource, mapper) }
        ).flow
    }
}
