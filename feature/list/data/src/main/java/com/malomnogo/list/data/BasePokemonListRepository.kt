package com.malomnogo.list.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.malomnogo.data.PokemonCloudDataSource
import com.malomnogo.domain.PokemonDomain
import com.malomnogo.domain.PokemonListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BasePokemonListRepository @Inject constructor(
    private val cloudDataSource: PokemonCloudDataSource,
    private val mapper: PokemonCloudMapper<PokemonDomain>,
) : PokemonListRepository {
    override fun fetchPokemonList(): Flow<PagingData<PokemonDomain>> =
        Pager(
            config =
                PagingConfig(
                    pageSize = 30,
                    enablePlaceholders = false,
                ),
            pagingSourceFactory = {
                PokemonPagingSource(
                    cloudDataSource = cloudDataSource,
                    mapper = mapper,
                )
            },
        ).flow
}
