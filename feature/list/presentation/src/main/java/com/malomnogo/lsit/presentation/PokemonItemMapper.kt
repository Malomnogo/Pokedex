package com.malomnogo.lsit.presentation

import com.malomnogo.GeneratePokemonImageUrl
import com.malomnogo.domain.PokemonDomain

interface PokemonItemMapper {
    fun map(input: PokemonDomain): PokemonUiItem

    class Base(
        private val generateImage: GeneratePokemonImageUrl,
    ) : PokemonItemMapper {
        override fun map(input: PokemonDomain): PokemonUiItem =
            with(input) {
                PokemonUiItem(
                    id = id,
                    name = name,
                    imageUrl = generateImage.generateUrl(id),
                )
            }
    }
}
