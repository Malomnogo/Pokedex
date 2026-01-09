package com.malomnogo.data

import com.malomnogo.model.PokemonCloud
import com.malomnogo.network.PokemonService
import javax.inject.Inject

interface PokemonCloudDataSource {
    suspend fun fetchPokemonList(page: Int): List<PokemonCloud>

    class Base
        @Inject
        constructor(
            private val service: PokemonService,
        ) : PokemonCloudDataSource {
            companion object {
                private const val PAGE_SIZE = 20
            }

            override suspend fun fetchPokemonList(page: Int): List<PokemonCloud> {
                val response =
                    service.fetchPokemonList(
                        offset = page * PAGE_SIZE,
                        limit = PAGE_SIZE,
                    )
                return response.results
            }
        }
}
