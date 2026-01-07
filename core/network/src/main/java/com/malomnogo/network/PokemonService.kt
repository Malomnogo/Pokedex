package com.malomnogo.network

import com.malomnogo.model.PokemonCloud
import com.malomnogo.model.core.BasePagingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): BasePagingResponse<PokemonCloud>
}
