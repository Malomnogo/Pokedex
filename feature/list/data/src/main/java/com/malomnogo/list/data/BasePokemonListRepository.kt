package com.malomnogo.list.data

import com.malomnogo.common.AppDispatchers
import com.malomnogo.common.HandleError
import com.malomnogo.data.PokemonCloudDataSource
import com.malomnogo.domain.PokemonListRepository
import com.malomnogo.domain.PokemonListResult
import kotlinx.coroutines.withContext

class BasePokemonListRepository(
    private val cloudDataSource: PokemonCloudDataSource,
    private val handleError: HandleError,
    private val mapper: BasePokemonCloudMapper, // Конкретный класс вместо дженерика
    private val dispatchers: AppDispatchers
) : PokemonListRepository {

    override suspend fun fetchPokemonList(): PokemonListResult = withContext(dispatchers.io) {
        try {
            val pokemonList = cloudDataSource.fetchPokemonList(0)
            val domainList = pokemonList.map { mapper.map(it) }
            PokemonListResult.Success(domainList)
        } catch (e: Exception) {
            PokemonListResult.Error(handleError.handle(e))
        }
    }
}
