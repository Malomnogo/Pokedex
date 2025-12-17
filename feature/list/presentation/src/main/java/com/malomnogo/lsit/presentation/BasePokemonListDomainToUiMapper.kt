package com.malomnogo.lsit.presentation

import com.malomnogo.domain.PokemonDomain
import com.malomnogo.domain.PokemonListResult

class BasePokemonListDomainToUiMapper(
    private val pokemonMapper: PokemonItemMapper
) : PokemonListResult.Mapper<PokemonListUiState> {

    override fun mapSuccess(pokemonList: List<PokemonDomain>) = PokemonListUiState.Base(
        pokemonList = pokemonList.map { pokemon -> pokemonMapper.map(pokemon) }
    )

    override fun mapError(message: String) = PokemonListUiState.Error(message = message)
}
